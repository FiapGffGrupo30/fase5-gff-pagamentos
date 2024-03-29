package br.fiap.gff.payments.usecases.services;

import br.fiap.gff.payments.application.events.CustomerNotifyEvent;
import br.fiap.gff.payments.dto.PaymentSpecification;
import br.fiap.gff.payments.dto.ReceiptRequest;
import br.fiap.gff.payments.dto.TransactionEvent;
import br.fiap.gff.payments.dto.TransactionProperties;
import br.fiap.gff.payments.exceptions.TransactionNotAllowedException;
import br.fiap.gff.payments.usecases.ReceiptUseCase;
import br.fiap.gff.payments.usecases.TransactionUseCase;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class TransactionService implements TransactionUseCase {

    private final ReceiptUseCase receipts;
    private final CustomerNotifyEvent notifyCustomer;
    private final ValueCommands<String, TransactionProperties> redisCommand;

    @SuppressWarnings("CdiInjectionPointsInspection")
    public TransactionService(ReceiptUseCase receipts, CustomerNotifyEvent notifyCustomer, RedisDataSource ds) {
        this.receipts = receipts;
        this.notifyCustomer = notifyCustomer;
        this.redisCommand = ds.value(TransactionProperties.class);
    }


    @Override
    public void execute(TransactionEvent event) {
        TransactionProperties.Wallet wallet = verify(event.transactionId(), event.customerId());
        PaymentSpecification paymentSpecification = getPaymentMethodFromWallet(wallet);
        ReceiptRequest receiptRequest = eventToReceiptRequest(event);
        boolean success = receipts.create(receiptRequest, paymentSpecification);
        notifyEvent(event, success);
    }

    private static PaymentSpecification getPaymentMethodFromWallet(TransactionProperties.Wallet wallet) {
        return PaymentSpecification.builder()
                .paymentMethod(wallet.paymentMethod())
                .cardNumber(wallet.cardNumber())
                .cardName(wallet.cardName())
                .expirationDate(wallet.expirationDate())
                .securityCode(wallet.securityCode())
                .build();
    }


    private TransactionProperties.Wallet verify(UUID transactionId, Long customerId) {
        TransactionProperties properties = redisCommand.get(transactionId.toString());
        if (properties == null || !properties.customerId().equals(customerId))
            throw new TransactionNotAllowedException(customerId, transactionId);
        return properties.wallet();
    }

    private static ReceiptRequest eventToReceiptRequest(TransactionEvent event) {
        return ReceiptRequest.builder()
                .transactionID(event.transactionId())
                .customerId(event.customerId())
                .orderId(event.orderId())
                .total(event.orderPrice())
                .build();
    }

    private void notifyEvent(TransactionEvent event, boolean success) {
        if (!success) {
            event = event.toBuilder().status("FAILED").build();
            notifyCustomer.send(event);
        }
        event = event.toBuilder().status("SUCCESS").build();
        notifyCustomer.send(event);
    }
}

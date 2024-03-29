package br.fiap.gff.payments.usecases.services;

import br.fiap.gff.payments.application.events.CustomerNotifyEvent;
import br.fiap.gff.payments.dto.ReceiptRequest;
import br.fiap.gff.payments.dto.TransactionEvent;
import br.fiap.gff.payments.usecases.ReceiptUseCase;
import br.fiap.gff.payments.usecases.TransactionUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final ReceiptUseCase receipts;
    private final CustomerNotifyEvent notifyCustomer;

    @Override
    public void execute(TransactionEvent event) {
        ReceiptRequest receiptRequest = eventToReceiptRequest(event);
        boolean success = receipts.create(receiptRequest);
        notifyEvent(event, success);
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

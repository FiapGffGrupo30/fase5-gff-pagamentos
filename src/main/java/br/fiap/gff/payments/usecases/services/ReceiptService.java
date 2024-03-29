package br.fiap.gff.payments.usecases.services;

import br.fiap.gff.payments.dto.PaymentSpecification;
import br.fiap.gff.payments.dto.ReceiptRequest;
import br.fiap.gff.payments.models.Receipt;
import br.fiap.gff.payments.models.Status;
import br.fiap.gff.payments.repository.ReceiptRepository;
import br.fiap.gff.payments.usecases.PaymentUseCase;
import br.fiap.gff.payments.usecases.ReceiptUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class ReceiptService implements ReceiptUseCase {

    private final ReceiptRepository repository;
    private final PaymentUseCase payment;

    @Override
    public boolean create(ReceiptRequest request, PaymentSpecification paymentSpecification) {
        // Verify idempontecy
        if (repository.count("transactionId", request.transactionID()) > 0) {
            return true; // Receipt already created
        }
        // Create the receipt of transaction
        Receipt r = save(request);
        // Process the payment
        boolean success = payment.process(r, paymentSpecification);
        updateReceiptStatus(success, r);
        return success;
    }

    @Override
    public Receipt getByTransactionId(UUID transactionId) {
        return null;
    }

    @Override
    public Receipt getById(String id) {
        return null;
    }

    private Receipt save(ReceiptRequest request) {
        Receipt r = Receipt.builder()
                .customerId(request.customerId())
                .orderId(request.orderId())
                .transactionId(request.transactionID())
                .total(request.total())
                .status(Status.PENDING)
                .build();
        repository.persist(r);
        return r;
    }

    private void updateReceiptStatus(boolean success, Receipt r) {
        if (!success) {
            setReceiptFailed(r);
        } else {
            setReceiptPaid(r);
        }
    }


    private void setReceiptFailed(Receipt r) {
        r = r.toBuilder().status(Status.FAILED).build();
        repository.update(r);
    }

    private void setReceiptPaid(Receipt r) {
        r = r.toBuilder().status(Status.PAID).build();
        repository.update(r);
    }


}

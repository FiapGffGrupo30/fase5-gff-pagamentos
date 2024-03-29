package br.fiap.gff.payments.usecases;

import br.fiap.gff.payments.dto.PaymentSpecification;
import br.fiap.gff.payments.dto.ReceiptRequest;
import br.fiap.gff.payments.models.Receipt;

import java.util.UUID;

public interface ReceiptUseCase {

    boolean create(ReceiptRequest request, PaymentSpecification paymentSpecification);

    Receipt getByTransactionId(String transactionId);

    Receipt getById(String id);
}

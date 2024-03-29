package br.fiap.gff.payments.usecases;

import br.fiap.gff.payments.dto.ReceiptRequest;
import br.fiap.gff.payments.models.Receipt;

import java.util.UUID;

public interface ReceiptUseCase {

    void create(ReceiptRequest event);

    Receipt getByTransactionId(UUID transactionId);

    Receipt getById(String id);
}

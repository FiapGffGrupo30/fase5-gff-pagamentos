package br.fiap.gff.payments.dto;

import java.util.UUID;

public record ReceiptRequest(
        Long customerId,
        Long orderId,
        UUID transactionID,
        Double total
) {
}

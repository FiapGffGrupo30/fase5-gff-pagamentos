package br.fiap.gff.payments.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ReceiptRequest(
        Long customerId,
        Long orderId,
        UUID transactionID,
        Double total
) {
}

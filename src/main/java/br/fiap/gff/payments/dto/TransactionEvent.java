package br.fiap.gff.payments.dto;

import java.util.UUID;

public record TransactionEvent(
        UUID transactionId,
        Long customerId,
        Long orderId,
        Double orderPrice,
        Item[] items,
        String status
) {
    public record Item(Long productId, Integer quantity) {
    }
}

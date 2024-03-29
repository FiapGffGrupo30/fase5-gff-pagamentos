package br.fiap.gff.payments.dto;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
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

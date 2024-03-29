package br.fiap.gff.payments.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TransactionProperties(Long customerId, PaymentMethod paymentMethod) {
    public record PaymentMethod(
            String paymentMethod,
            String cardNumber,
            String cardName,
            String expirationDate,
            String securityCode,
            Boolean main,
            LocalDateTime createdAt
    ) { }
}

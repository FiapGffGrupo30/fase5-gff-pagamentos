package br.fiap.gff.payments.dto;

import lombok.Builder;

@Builder
public record PaymentSpecification(
    String paymentMethod,
    String cardNumber,
    String cardName,
    String expirationDate,
    String securityCode) {
}

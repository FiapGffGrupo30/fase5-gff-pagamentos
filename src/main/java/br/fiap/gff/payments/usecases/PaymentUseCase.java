package br.fiap.gff.payments.usecases;

import br.fiap.gff.payments.dto.PaymentSpecification;
import br.fiap.gff.payments.models.Receipt;

public interface PaymentUseCase {
    boolean process(Receipt receipt, PaymentSpecification paymentSpecification);
}

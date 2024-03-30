package br.fiap.gff.payments.usecases.services;

import br.fiap.gff.payments.dto.PaymentSpecification;
import br.fiap.gff.payments.models.Receipt;
import br.fiap.gff.payments.usecases.PaymentUseCase;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

@ApplicationScoped
public class PaymentService implements PaymentUseCase {
    @Override
    public boolean process(Receipt receipt, PaymentSpecification paymentSpecification) {
        Log.info("Processing payment");
        return true;
    }
}

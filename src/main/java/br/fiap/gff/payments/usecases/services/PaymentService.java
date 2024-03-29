package br.fiap.gff.payments.usecases.services;

import br.fiap.gff.payments.models.Receipt;
import br.fiap.gff.payments.usecases.PaymentUseCase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

@ApplicationScoped
public class PaymentService implements PaymentUseCase {
    @Override
    public boolean process(Receipt receipt) {
        try {
            Thread.sleep(5000);
            return new Random().nextBoolean();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}

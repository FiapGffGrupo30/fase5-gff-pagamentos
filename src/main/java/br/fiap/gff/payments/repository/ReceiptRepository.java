package br.fiap.gff.payments.repository;

import br.fiap.gff.payments.models.Receipt;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReceiptRepository implements PanacheMongoRepository<Receipt> {
}

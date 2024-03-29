package br.fiap.gff.payments.repository;

import br.fiap.gff.payments.models.Receipt;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

public class ReceiptRepository implements PanacheMongoRepository<Receipt> {
}

package br.fiap.gff.payments.usecases;

import br.fiap.gff.payments.dto.TransactionEvent;

public interface TransactionUseCase {
    void execute(TransactionEvent event);
}

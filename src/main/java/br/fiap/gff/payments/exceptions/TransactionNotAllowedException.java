package br.fiap.gff.payments.exceptions;

import br.fiap.gff.payments.exceptions.base.DomainException;

import java.util.UUID;

public class TransactionNotAllowedException extends DomainException {
    public TransactionNotAllowedException(UUID transactionId) {
        super(String.format("Transaction %s is not allowed", transactionId));
    }
}

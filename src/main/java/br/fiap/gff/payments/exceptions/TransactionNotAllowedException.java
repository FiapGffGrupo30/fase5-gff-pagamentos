package br.fiap.gff.payments.exceptions;

import br.fiap.gff.payments.exceptions.base.DomainException;

import java.util.UUID;

public class TransactionNotAllowedException extends DomainException {
    public TransactionNotAllowedException(Long customerId, UUID transactionId) {
        super(String.format("Transaction not allowed for Customer ID: %s, Transaction ID: %s", customerId, transactionId));
    }
}

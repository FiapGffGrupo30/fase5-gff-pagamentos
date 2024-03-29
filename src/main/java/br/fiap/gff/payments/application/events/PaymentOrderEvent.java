package br.fiap.gff.payments.application.events;

import br.fiap.gff.payments.dto.TransactionEvent;
import br.fiap.gff.payments.usecases.TransactionUseCase;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
@RequiredArgsConstructor
public class PaymentOrderEvent {

    private final TransactionUseCase transaction;

    @Incoming("pay")
    @Blocking
    public void hanlde(JsonObject message) {
        TransactionEvent event = message.mapTo(TransactionEvent.class);
        transaction.execute(event);
    }

}

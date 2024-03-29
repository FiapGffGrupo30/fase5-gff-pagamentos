package br.fiap.gff.payments.models;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@MongoEntity(collection = "receipts")
public class Receipt {

    private ObjectId id;
    private Long customerId;
    private Long orderId;
    private UUID transactionId;
    private Double total;
    private Status status;
    private LocalDate paymentDate;

}

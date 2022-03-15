package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("transaction")
public class Transaction {
    @Id
    Integer id;
    Integer idAccount;
    Float amount;
    String date;
    TransactionType transactionType;
}

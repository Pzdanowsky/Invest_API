package com.Invest.system.Objects;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transaction_id;

    private long wallet_id;

    private long value_type_id;

    private long type_transaction_id;

    private float price_transaction;

    private float amount;

    private float value_usd;

    private Timestamp date_transaction;


}

package com.Invest.system.Objects;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;
@Entity
@Getter
@Setter
public class Wallets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wallet_id;

    private long owner_id;

    private String wallet_name;

    private boolean public_status;


    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="wallet_id", updatable = false, insertable = false)
    private Set<Transactions> transactions;

}

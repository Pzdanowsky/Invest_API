package com.Invest.system.Objects;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Valuestype {

    @Id
    private long value_id;

    private long asset_id;

    private String value_name;

}

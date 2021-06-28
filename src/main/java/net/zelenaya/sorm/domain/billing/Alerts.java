package net.zelenaya.sorm.domain.billing;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table
public class Alerts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String alert;
    private String symbol;
    private Double price;
    private Double rate;
    private Date date;
}

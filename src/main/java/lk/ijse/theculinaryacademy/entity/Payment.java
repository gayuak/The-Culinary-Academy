package lk.ijse.theculinaryacademy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long payId;

    @Column(name = "pay_date", nullable = false)
    private String payDate;

    @Column(name = "pay_amount", nullable = false)
    private double payAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "upfront_amount", nullable = false)
    private double upfrontAmount;

    @Column(name = "balance_amount")
    private double balanceAmount;
}


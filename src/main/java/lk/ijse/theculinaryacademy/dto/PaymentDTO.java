package lk.ijse.theculinaryacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentDTO {

    private Long pay_id;

    private String pay_date;

    private double pay_amount;

    private String status;

    private double upfront_amount;

    private double balance_amount;

}

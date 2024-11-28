package lk.ijse.theculinaryacademy.entity.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CartTm {
    private String id;
    private String courseName;
    private String courseFee;
    private JFXButton action;
}

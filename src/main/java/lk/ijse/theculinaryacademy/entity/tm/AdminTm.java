package lk.ijse.theculinaryacademy.entity.entityTm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminTm {
    private String userId;
    private String userName;
    private String role;
    private JFXButton action;
}

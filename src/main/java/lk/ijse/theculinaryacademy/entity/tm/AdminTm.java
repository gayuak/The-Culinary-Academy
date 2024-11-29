package lk.ijse.theculinaryacademy.entity.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminTm {
    private Long userId;
    private String name;
    private String role;
}

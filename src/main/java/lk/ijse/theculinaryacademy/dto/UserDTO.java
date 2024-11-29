package lk.ijse.theculinaryacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDTO {
    private Long userid;
    private String username;
    private String password;
    private String jobrole;

}
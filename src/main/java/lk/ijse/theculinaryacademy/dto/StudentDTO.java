package lk.ijse.theculinaryacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO{

    private Long StudentId;
    private String Name;
    private String address;
    private String contact;

    private UserDTO user;


}

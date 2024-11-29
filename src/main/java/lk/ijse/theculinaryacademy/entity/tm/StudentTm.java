package lk.ijse.theculinaryacademy.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTm {
    private Long id;
    private String name;
    private String address;
    private String contact;
}

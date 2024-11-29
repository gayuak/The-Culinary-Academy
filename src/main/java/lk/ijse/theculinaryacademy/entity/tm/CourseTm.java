package lk.ijse.theculinaryacademy.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseTm {

    private Long cid;
    private String cname;
    private String duration;
    private String price;
}

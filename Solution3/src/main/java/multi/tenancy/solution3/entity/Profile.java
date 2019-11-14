package multi.tenancy.solution3.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class Profile {
    private Long id;
    private Long tenantId;
    private String title;
    private String content;
}

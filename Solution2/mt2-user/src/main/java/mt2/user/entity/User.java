package mt2.user.entity;


import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class User {
    private Long id;
    private Long tenantId;
    private String name;
}

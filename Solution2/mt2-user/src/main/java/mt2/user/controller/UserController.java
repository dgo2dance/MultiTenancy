package mt2.user.controller;

import mt2.user.entity.User;
import mt2.user.mapper.UserMapper;
import mt2.user.utils.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TenantContext tenantContext;

    @Autowired
    private UserMapper userMapper;

    private static Random random = new Random();

    @GetMapping("/login/{id}")
    public String login(@PathVariable String id) {
        User user = userMapper.selectById(id);
        String token = String.valueOf(random.nextInt(1000));

        tenantContext.putTokenTenantIdPair(token,user.getTenantId());
        return "login success, token is " + token + " tenant id is " + user.getTenantId();
    }

    @GetMapping("/getTenantIdByToken/{token}")
    public String getTenantIdByToken(@PathVariable String token) {

        Long tenantId = tenantContext.getTenantIdWithToken(token);

        return "operate success, token is " + token + " tenant id is " + tenantId;
    }

    @GetMapping("/tenantIdByToken/{token}")
    public Long tenantIdByToken(@PathVariable("token") String token) {
        return tenantContext.getTenantIdWithToken(token);
    }
}

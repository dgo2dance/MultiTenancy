package multi.tenancy.solution3.controller;

import multi.tenancy.solution3.entity.User;
import multi.tenancy.solution3.mapper.UserMapper;
import multi.tenancy.solution3.utils.TenantContext;
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

        //直接根据用户id号查询出用户信息
        User user = userMapper.selectById(id);

        //生成一个1000以内的随机数，作为用户登录后返回给用户的token
        String token = String.valueOf(random.nextInt(1000));

        //将token、tenantId键值对放入tenantContext
        tenantContext.putTokenTenantIdPair(token,user.getTenantId());

        return "login success, token is " + token + " tenant id is " + user.getTenantId();
    }
}

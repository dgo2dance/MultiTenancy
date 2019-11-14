package mt2.profile.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="mt2-user")
public interface UserClient {

    @GetMapping("/user/tenantIdByToken/{token}")
    public Long tenantIdByToken(@PathVariable("token") String token);
}

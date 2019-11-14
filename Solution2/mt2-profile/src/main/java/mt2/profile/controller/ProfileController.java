package mt2.profile.controller;

import mt2.profile.entity.Profile;
import mt2.profile.mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileMapper profileMapper;

    @GetMapping("/findAll/{token}")
    public String findAll(@PathVariable String token) {

        //prepareTenantContext(token);

        List<Profile> profiles = profileMapper.selectList(null);
        profiles.forEach(System.out::println);
        return "operation complete, the following is the result \n" + profiles.toString();
    }

    @GetMapping("/add/{token}")
    public String add(@PathVariable String token) {

        Profile p = new Profile();
        p.setId((long) 3);
        p.setTitle("3号档案");
        p.setContent("3号档案");

        int result = profileMapper.insert(p);
        return "operation complete, the following is the result \n" + String.valueOf(result);
    }

    @GetMapping("/update/{token}")
    public String update(@PathVariable String token) {

        Profile p = new Profile();
        p.setId((long) 3);
        p.setTitle("4号档案");
        p.setContent("4号档案");

        int result = profileMapper.updateById(p);
        return "operation complete, the following is the result \n" + String.valueOf(result);
    }

    @GetMapping("/delete/{token}")
    public String delete(@PathVariable String token) {

        int result = profileMapper.deleteById((long)3);
        return "operation complete, the following is the result \n" + String.valueOf(result);
    }
}

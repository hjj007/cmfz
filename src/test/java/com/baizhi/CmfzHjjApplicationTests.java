package com.baizhi;

import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import com.baizhi.service.MenuService;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzHjjApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    BannerService bannerService;
    @Autowired
    AlbumService albumService;
    @Test
    public void contextLoads() {
		/*User user=new User();
		user.setPhone("1");
		user.setPassword("1");
        User login = userService.login(user);
        System.out.println(login);*/
        System.out.println(albumService.selectall());
    }

}

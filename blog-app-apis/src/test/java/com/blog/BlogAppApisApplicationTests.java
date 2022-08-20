package com.blog;

import com.blog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Test
    void contextLoads() {
    }
    @Test
    public void repoTest(){
        String classname = this.userRepository.getClass().getName();
        String packName = this.userRepository.getClass().getPackageName();
        System.out.println(classname);
        System.out.println(packName);

    }

}

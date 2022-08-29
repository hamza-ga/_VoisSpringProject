package com.blog;

import com.blog.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication {// implements CommandLineRunner

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
//        List<OpjectLists> objects = new ArrayList<>();
//        OpjectLists firstOpject = new OpjectLists();
//        OpjectLists secoundOpject =null ;
//        OpjectLists thirdOpject = null ;
//        firstOpject.setId(2);
//        firstOpject.setName("hamza");
//        secoundOpject.setId(3);
//        secoundOpject.setName("khaled");
//        thirdOpject.setId(4);
//        thirdOpject.setName("eslam");
//        objects.add(1,firstOpject);
//        objects.add(2,secoundOpject);
//        objects.add(3,thirdOpject);
//        System.out.println(objects);
//
//        //OpjectLists result = methods(objects);
//

    }


//    class OpjectLists {
//        private int id;
//        private String name;
//
//
//
//        public OpjectLists(int id, String name){
//            this.id =id;
//            this.name = name;
//        }
//
//        public int getId() {
//            return id;
//        }
//        public String getName(){
//            return name;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public void setName(String name){
//            this.name= name;
//        }
//    }

   // OpjectLists input = new OpjectLists(2,"hamza");
//    public static OpjectLists methods(List<OpjectLists> objectList){
//       // objectList.stream().filter(s)
//
//    }

//    @Override
//    public void run(String... args) throws Exception{
//        System.out.println(this.passwordEncoder.encode("password"));
//
//        try{
//            Role role1 = new Role();
//            role1.setId(AppConstants.ADMIN_USER);
//            role1.setName("ADMIN_USER");
//
//            Role role2 = new Role();
//            role2.setId(AppConstants.NORMAL_USER);
//            role2.setName("NORMAL_USER");
//
//            List<Role> roles = List.of(role1,role2);
//            List<Role> result = this.roleRepository.saveAll(roles);
//            result.forEach(r ->{
//                System.out.println(r.getName());
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}

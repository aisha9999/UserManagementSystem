package az.project.usermanagementsystem;

import az.project.usermanagementsystem.service.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class UserManagementSystemApplication implements CommandLineRunner {
@Resource
    FileService fileService;
    public static void main(String[] args) {
        SpringApplication.run(UserManagementSystemApplication.class, args);
    }
    @Override
    public void run(String... arg) throws Exception {
        fileService.deleteAll();
        fileService.init();
    }
}

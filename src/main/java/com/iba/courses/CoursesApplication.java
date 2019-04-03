package com.iba.courses;

import com.iba.courses.service.FTPService;
import com.iba.courses.service.IMSService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursesApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CoursesApplication.class, args);
        /*IMSService service=new IMSService();
        service.init();
        service.connect();
        System.out.println(service.execute("/DIS TRAN ADDINV"));*/
        FTPService ftpService=new FTPService();
        ftpService.execute("job");
    }

}

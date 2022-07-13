package ru.avbugorov.vaadinaw;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@SpringBootApplication
//@Theme(value = "flowcrmtutorial")
//@PWA(name = "Flow CRM Tutorial", shortName = "Flow CRM Tutorial", offlineResources = {})
//@NpmPackage(value = "line-awesome", version = "1.3.0")
public class VaadinAwApplication extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(VaadinAwApplication.class, args);
        System.out.println("In test mode, see data console from url: http://localhost:1313/h2-console/");
    }
}

package net.zelenaya.sorm.controller;

import net.zelenaya.sorm.config.PersistenceBillingConfiguration;
import net.zelenaya.sorm.domain.billing.Alerts;
import net.zelenaya.sorm.repo.billing.AlertsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    AlertsRepo billingRepo;
    @Autowired
    ApplicationContext context;
    @Autowired
    ConfigurableEnvironment env;

    @GetMapping
    public String getMain(){
        return "main";
    }

    @GetMapping("/log")
    public String getLog(){

//        System.out.println(settingsAppDBImpl);
//        PersistenceBillingConfiguration pbc = context.getBean(PersistenceBillingConfiguration.class);
//        pbc.billingDataSourceSet(settingsAppDBImpl);


        List<Alerts> alerts = billingRepo.findAll();
        System.out.println(alerts.size());


        return "Test";
    }

    @GetMapping("/log1")
    public String getLog1() throws SQLException {

        PersistenceBillingConfiguration pbc = context.getBean(PersistenceBillingConfiguration.class);
        System.out.println(pbc.billingDataSource().getConnection().getSchema());

        List<Alerts> alerts = billingRepo.findAll();
        System.out.println(alerts.size());

        return "ok";
    }
}

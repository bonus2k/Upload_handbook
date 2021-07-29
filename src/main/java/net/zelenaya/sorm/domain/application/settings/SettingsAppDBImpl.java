package net.zelenaya.sorm.domain.application.settings;

import lombok.Data;
import net.zelenaya.sorm.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@PropertySource(value = {"file:settings.properties"})
public class SettingsAppDBImpl implements SettingsApp {
    @Value("${billing.jdbc.driverClassName}")
    private String driverClassName;

    @Value("${billing.jdbc.url}")
    private String url;

    @Value("${billing.jdbc.username}")
    private String username;

    @Value("${billing.jdbc.password}")
    private String password;

    @Value("${billing.hibernate.dialect}")
    private String dialect;

    @Value("${billing.hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Override
    public void store(Map<String, String> form) {
        DriverJDBC driverJDBC = DriverJDBC.valueOf(form.get("billing.jdbc.driverClassName"));
        String passwordForm = form.get("billing.jdbc.password");
        String URLForm = form.get("billing.jdbc.url");
        form.put("billing.jdbc.url", driverJDBC.getURL() + "//" + URLForm);
        form.put("billing.jdbc.driverClassName", driverJDBC.getDriverName());

        this.username=form.get("billing.jdbc.username");

        this.driverClassName=form.get("billing.jdbc.driverClassName");
        this.url=form.get("billing.jdbc.url");

        if (password.equals(passwordForm)) {
            form.remove("billing.jdbc.password");
        } else {
            this.password=passwordForm;
            form.put("billing.jdbc.password", Util.encryptString(passwordForm));
        }
        form.remove("_csrf");

        Util.setProperties(form);
    }
}

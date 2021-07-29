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
public class SettingsAppFTPImpl implements SettingsApp {

    @Value("${FtpServer}")
    private String FtpServer;

    @Value("${FtpPort}")
    private Integer FtpPort;

    @Value("${FtpUser}")
    private String FtpUser;

    @Value("${FtpPassword}")
    private String FtpPassword;

    @Override
    public void store(Map<String, String> form) {

        String passwordForm = form.get("FtpPassword");


        this.FtpServer=form.get("FtpServer");
        this.FtpPort=Integer.parseInt(form.get("FtpPort"));
        this.FtpUser=form.get("FtpUser");


        if (FtpPassword.equals(passwordForm)) {
            form.remove("FtpPassword");
        } else {
            this.FtpPassword=form.get("FtpPassword");
            form.put("FtpPassword", Util.encryptString(passwordForm));
        }
        form.remove("_csrf");
        Util.setProperties(form);
    }
}

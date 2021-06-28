package net.zelenaya.sorm.domain.application;

import lombok.Data;
import net.zelenaya.sorm.domain.SettingsApp;

@Data
public class SettingsAppDBImpl implements SettingsApp {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String dialect;
    private String hbm2ddl;
}

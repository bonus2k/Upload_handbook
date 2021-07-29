package net.zelenaya.sorm.domain.application.settings;

import java.util.Map;

public interface SettingsApp {
    void store(Map<String, String> form);
}

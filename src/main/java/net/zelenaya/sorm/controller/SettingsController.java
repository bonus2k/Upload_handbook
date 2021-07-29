package net.zelenaya.sorm.controller;

import net.zelenaya.sorm.config.PersistenceBillingConfiguration;
import net.zelenaya.sorm.domain.application.settings.DriverJDBC;
import net.zelenaya.sorm.domain.application.settings.SettingsAppDBImpl;
import net.zelenaya.sorm.domain.application.settings.SettingsAppFTPImpl;
import net.zelenaya.sorm.domain.billing.UserBil;
import net.zelenaya.sorm.repo.billing.UserBilRepo;
import net.zelenaya.sorm.service.UserBilService;
import net.zelenaya.sorm.util.AppFTP;
import net.zelenaya.sorm.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class SettingsController {

    private final AppFTP appFTP;
    private final ApplicationContext context;
    private final SettingsAppDBImpl settingsAppDB;
    private final SettingsAppFTPImpl settingsAppFTP;
    private final UserBilRepo userBilRepo;

    @Autowired
    UserBilService userBilService;

    public SettingsController(ApplicationContext context,
                              SettingsAppDBImpl settingsAppDB,
                              SettingsAppFTPImpl settingsAppFTP,
                              AppFTP appFTP, UserBilRepo userBilRepo) {
        this.context = context;
        this.settingsAppDB = settingsAppDB;
        this.settingsAppFTP = settingsAppFTP;
        this.appFTP = appFTP;
        this.userBilRepo = userBilRepo;
    }

    @GetMapping("/settings")
    public String getSettingsDB(Model model) {
        model.addAttribute("settingsAppDB", settingsAppDB);
        model.addAttribute("settingsAppFTP", settingsAppFTP);
        model.addAttribute("billingURL", Util.getURL(settingsAppDB.getUrl()));
        model.addAttribute("driverApp", Util.getDriver(settingsAppDB.getDriverClassName()));
        model.addAttribute("drivers", DriverJDBC.values());
        return "settings";
    }

    @PostMapping("/settings_db")
    public String setSettingsDB(@RequestParam Map<String, String> form, Model model) {
        settingsAppDB.store(form);
        PersistenceBillingConfiguration pbc =
                context.getBean(PersistenceBillingConfiguration.class);
        pbc.billingDataSourceSet(settingsAppDB);
        List<UserBil> list = userBilRepo.findAll();
        List <String> str = userBilService.getAbonentFile(list);
        str.stream().limit(100).forEach(System.out::println);
        try {
//


//
            model.addAttribute("messageBil",
                    String.format("Подключение к БД настроено верно." +
                            " Найдено %s записей в таблице User", userBilRepo.count()));
            model.addAttribute("alertBil", "success");
            return getSettingsDB(model);
        } catch (Exception e) {
            model.addAttribute("messageBil", "Ошибка в подключении к БД. " +
                    "Проверьте параметры подключения к БД");
            model.addAttribute("alertBil", "danger");
            return getSettingsDB(model);
        }
    }

    @PostMapping("/settings_ftp")
    public String setSettingsFTP(@RequestParam Map<String, String> form, Model model) {
        settingsAppFTP.store(form);
        Integer code = appFTP.list();
        if (code == 150) {
            model.addAttribute("messageFtp",
                    String.format("Подключение к FTP настроено верно. " +
                            "Код подключения %s", code));
            model.addAttribute("alertFtp", "success");
            return getSettingsDB(model);
        } else {
            model.addAttribute("messageFtp",
                    String.format("Не удалось подключится к FTP. " +
                            "Код подключения %s", code));
            model.addAttribute("alertFtp", "danger");
            return getSettingsDB(model);
        }
    }
}

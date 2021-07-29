package net.zelenaya.sorm.util;

import net.zelenaya.sorm.domain.application.settings.DriverJDBC;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

public class Util {
    private static StandardPBEStringEncryptor encryptor;
    private static Path pathProperties = Paths.get("settings.properties");
    private static String password = "QDI$xpl@xQ@D58Y";
    private static String algorithm = "PBEWithMD5AndTripleDES";

    public static void createProperties() {
        Properties properties = new Properties();

        if (!Files.exists(pathProperties)) {
            System.out.printf("Создаю фаил %s \n", pathProperties.toAbsolutePath());
            properties.setProperty("billing.jdbc.driverClassName", "org.postgresql.Driver");
            properties.setProperty("billing.jdbc.url", "jdbc:postgresql://localhost/sorm");
            properties.setProperty("billing.jdbc.username", "postgres");
            properties.setProperty("billing.jdbc.password", encryptString("71282254"));
            properties.setProperty("billing.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.setProperty("billing.hibernate.hbm2ddl.auto", "none");
            properties.setProperty("FtpServer", "127.0.0.1");
            properties.setProperty("FtpPort", "21");
            properties.setProperty("FtpUser", "");
            properties.setProperty("FtpPassword", encryptString(""));

            try (OutputStream fos = new FileOutputStream(pathProperties.toFile())) {
                properties.store(fos, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.printf("Фаил %s создан \n", pathProperties.toAbsolutePath());
        } else System.out.printf("Фаил %s существует \n", pathProperties.toAbsolutePath());
    }

    public static void setProperties(Map<String, String> map) {
        Properties properties = new Properties();
        Boolean isModify = false;
        try (InputStream fis = new FileInputStream(pathProperties.toFile())) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> prop : map.entrySet()) {
            if (!prop.getValue().isEmpty() && !prop.getValue().equals(properties.getProperty(prop.getKey()))) {
                properties.setProperty(prop.getKey(), prop.getValue());
                isModify = true;
            }
        }
        if (isModify) {
            try (OutputStream fos = new FileOutputStream(pathProperties.toFile())) {
                properties.store(fos, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String encryptString(String hash) {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm(algorithm);
        return "ENC(" + encryptor.encrypt(hash) + ")";
    }

    public static String getURL(String url) {
        return url.replaceAll("(^.*)(//)", "");
    }

    public static String getDriver(String driverClassName) {
        for (DriverJDBC driver:DriverJDBC.values()) {
            if (driver.getDriverName().equals(driverClassName))
                return driver.name();
        }
        return null;
    }

    public static String getStringDate(Long actual_from) {
        Date date = new Date(actual_from);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

}

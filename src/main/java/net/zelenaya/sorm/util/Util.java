package net.zelenaya.sorm.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Util {
    private static StandardPBEStringEncryptor encryptor;
    private static Path pathProperties = Paths.get("db.properties");
    private static String password = "QDI$xpl@xQ@D58Y";
    private static String algorithm = "PBEWithMD5AndTripleDES";

    public static void createProperties() {
        Properties properties = new Properties();

        if (!Files.exists(pathProperties)) {
            properties.setProperty("billing.jdbc.driverClassName", "org.postgresql.Driver");
            properties.setProperty("billing.jdbc.url", "jdbc:postgresql://localhost/sorm");
            properties.setProperty("billing.jdbc.username", "postgres");
            properties.setProperty("billing.jdbc.password", encryptString("71282254"));
            properties.setProperty("billing.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.setProperty("billing.hibernate.hbm2ddl.auto", "update");
            try (OutputStream fos = new FileOutputStream(pathProperties.toFile())) {
                properties.store(fos, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Фаил создан");
        } else System.out.println("Фаил существует");
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

    public static Map<String, String> getPropertiesMap() {

        Properties properties = new Properties();
        try (InputStream fis = new FileInputStream(pathProperties.toFile())) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.entrySet().stream().collect(
                Collectors.toMap(
                        e -> e.getKey().toString(),
                        e -> e.getValue().toString()
                ));
    }

    public static String encryptString(String hash) {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm(algorithm);
        return "ENC(" + encryptor.encrypt(hash) + ")";
    }

    public static String decryptString(String hash) {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm(algorithm);
        return encryptor.decrypt(hash);
    }
}

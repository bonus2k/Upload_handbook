package net.zelenaya.sorm.util;

import net.zelenaya.sorm.domain.application.settings.SettingsAppFTPImpl;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class AppFTP {
    private FTPClient ftpClient = new FTPClient();

    final
    SettingsAppFTPImpl settingsAppFTP;

    public AppFTP(SettingsAppFTPImpl settingsAppFTP) {
        this.settingsAppFTP = settingsAppFTP;
    }

    public void send(String filename, String remoteFile) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            ftpClient.connect(settingsAppFTP.getFtpServer(), settingsAppFTP.getFtpPort());
            ftpClient.login(settingsAppFTP.getFtpUser(), settingsAppFTP.getFtpPassword());
            ftpClient.storeFile(remoteFile, fis);
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer list() {
        Integer code = null;

        try {
            ftpClient.connect(settingsAppFTP.getFtpServer(), settingsAppFTP.getFtpPort());
            ftpClient.login(settingsAppFTP.getFtpUser(), settingsAppFTP.getFtpPassword());
            code = ftpClient.list();
            ftpClient.logout();
        } catch (IOException e) {
            code = 550;
        }
        return code;
    }
}

package net.zelenaya.sorm.domain.application.settings;

public enum DriverJDBC {
    PostgresSQL("org.postgresql.Driver", "jdbc:postgresql:"),
    MySQL("com.mysql.jdbc.Driver", "jdbc:mysql:");

    private String driverName;
    private String URL;

    DriverJDBC(String driverName, String URL) {
        this.driverName = driverName;
        this.URL = URL;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getURL() {
        return URL;
    }
}

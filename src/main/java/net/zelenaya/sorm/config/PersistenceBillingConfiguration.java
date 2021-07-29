package net.zelenaya.sorm.config;

import net.zelenaya.sorm.domain.application.settings.SettingsAppDBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource(value = {"file:settings.properties"})
@EnableJpaRepositories(
        basePackages = "net.zelenaya.sorm.repo.billing",
        entityManagerFactoryRef = "billingEntityManager",
        transactionManagerRef = "billingTransactionManager"
)
public class PersistenceBillingConfiguration {
    @Autowired
    private Environment env;

    private DriverManagerDataSource dataSource
            = new DriverManagerDataSource();


    @Bean
    public LocalContainerEntityManagerFactoryBean billingEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(billingDataSource());
        em.setPackagesToScan(
                new String[] { "net.zelenaya.sorm.domain.billing" });


        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("billing.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("billing.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource billingDataSource() {
      dataSource.setDriverClassName(
                env.getProperty("billing.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("billing.jdbc.url"));
        dataSource.setUsername(env.getProperty("billing.jdbc.username"));
        dataSource.setPassword(env.getProperty("billing.jdbc.password"));
        return dataSource;
    }

    public void billingDataSourceSet(SettingsAppDBImpl settingsAppDBImpl){
        dataSource.setDriverClassName(settingsAppDBImpl.getDriverClassName());
        dataSource.setUrl(settingsAppDBImpl.getUrl());
        dataSource.setUsername(settingsAppDBImpl.getUsername());
        dataSource.setPassword(settingsAppDBImpl.getPassword());
    }

    @Bean
    public PlatformTransactionManager billingTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                billingEntityManager().getObject());
        return transactionManager;
    }
}
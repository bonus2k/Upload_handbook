package net.zelenaya.sorm.dao.billing;

import net.zelenaya.sorm.domain.billing.Alerts;
import net.zelenaya.sorm.repo.billing.AlertsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Configurable
@Service
public class BillingDao {

    @Autowired
    AlertsRepo billingRepo;


    public Alerts loadUserByUsername(Long s) throws UsernameNotFoundException {
        return billingRepo.findById(s).get();
    }
}

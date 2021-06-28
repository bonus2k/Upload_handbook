package net.zelenaya.sorm.repo.billing;

import net.zelenaya.sorm.domain.application.User;
import net.zelenaya.sorm.domain.billing.Alerts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlertsRepo extends JpaRepository<Alerts, Long> {

    Optional<Alerts> findById(Long id);
}

package net.zelenaya.sorm.repo.billing;


import net.zelenaya.sorm.domain.billing.UserBil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBilRepo extends JpaRepository<UserBil, Long> {

    @Override
    long count();
}

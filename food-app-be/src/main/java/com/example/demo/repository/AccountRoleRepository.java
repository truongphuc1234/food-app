package com.example.demo.repository;

import com.example.demo.model.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM account_role WHERE account_id= :accountId")
    Iterable<AccountRole> findAllByAccountId(Integer accountId);
}

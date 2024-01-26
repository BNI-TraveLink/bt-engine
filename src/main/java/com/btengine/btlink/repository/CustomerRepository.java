package com.btengine.btlink.repository;

import com.btengine.btlink.model.Customer;
import com.btengine.btlink.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query(value = "SELECT * FROM \"bt-link\".Customer c WHERE c.fk_Login = :fkLogin",nativeQuery = true)
    Optional<Customer> findcustomerByFkLogin(@Param("fkLogin") UUID fkLogin);

    @Query(value = "select c.* from \"bt-link\".customer c " +
            "inner join \"bt-link\".login l on c.fk_login = l.sk_login " +
            "where l.user_id = :userid",nativeQuery = true)
    Optional<Customer> getCustomerByUserId(@Param("userid") String userid);


}

package org.conan.bootpractice.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.conan.bootpractice.domain.entity.Subscriber;


public interface SubscriberRepository extends JpaRepository<Subscriber, String> {
    @EntityGraph(attributePaths = {"subscriberRoleList"})
    @Query("SELECT s FROM Subscriber s WHERE s.email=:email")
    Subscriber getWithRoles(@Param("email") String email);
}

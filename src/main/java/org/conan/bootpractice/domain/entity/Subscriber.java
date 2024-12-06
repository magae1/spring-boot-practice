package org.conan.bootpractice.domain.entity;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import org.conan.bootpractice.domain.enums.SubscriberRole;


@Entity
@Table(name = "subscriber")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "subscriberRoleList")
public class Subscriber {
    @Id
    private String email;

    private String pwd, nickname;

    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<SubscriberRole> subscriberRoleList = new ArrayList<>();

    public void addRole(SubscriberRole subscriberRole) {
        subscriberRoleList.add(subscriberRole);
    }

    public void clearRole() {
        subscriberRoleList.clear();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setSocial(boolean social) {
        this.social = social;
    }
}
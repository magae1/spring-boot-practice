package org.conan.bootpractice.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


@Getter
@Setter
@ToString
public class SubscriberDTO extends User {
    private String email, pwd, nickname;
    private boolean social;
    private final List<String> roleNames;

    public SubscriberDTO(String email, String pwd, String nickname, boolean social, List<String> roleNames) {
        super(email, pwd, roleNames.stream()
                .map(s -> new SimpleGrantedAuthority("ROLE_" + s))
                .collect(Collectors.toList()));
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.social = social;
        this.roleNames = roleNames;
    }

    public Map<String, Object> getClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("pwd", pwd);
        claims.put("nickname", nickname);
        claims.put("social", social);
        claims.put("roleNames", roleNames);
        return claims;
    }
}

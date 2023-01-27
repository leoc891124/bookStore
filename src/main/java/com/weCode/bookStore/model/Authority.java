package com.weCode.bookStore.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor

@Entity
public class Authority implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String authority;


    @ManyToOne(optional = false)
    private Usuario user;

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority(){}
}

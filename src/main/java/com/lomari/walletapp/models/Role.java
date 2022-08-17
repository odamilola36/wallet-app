package com.lomari.walletapp.models;

import com.lomari.walletapp.enums.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseClass{


    @OneToOne
    private User user;

    private UserRole role;
}

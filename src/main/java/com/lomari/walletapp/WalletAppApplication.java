package com.lomari.walletapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

@SpringBootApplication
public class WalletAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletAppApplication.class, args);
    }

}

// TODO: VALIDATE TOKEN CONTENTS ON JWT.IO
// TODO: CREATE ENVIRONMENT PROFILES (XML, AND OTHERS)
// TODO: create index on user table to index username field : Done
// TODO: CHECK WHY THERE'S PASSWORD SALT : Done
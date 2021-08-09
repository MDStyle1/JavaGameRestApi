package com.mds.gameserver.security;

public enum Permission {
    READ("read"),
    WRITE("write"),
    CREATORSCORES("creatorscores");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
package com.medicensoya.valetapp.domain;

public enum AppUserRole {
    TECH("Technician"),
    VALET("Valet"),
    ADMIN("Administrator");

    private String roleName;

    AppUserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

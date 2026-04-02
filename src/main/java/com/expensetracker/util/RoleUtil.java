package com.expensetracker.util;

public class RoleUtil {

    public static void checkAccess(String role, String... allowedRoles) {

        for (String r : allowedRoles) {
            if (r.equalsIgnoreCase(role)) {
                return;
            }
        }

        throw new RuntimeException("Access Denied");
    }
}
package domain.user;

import java.util.Arrays;

public enum ReservedRole {

    DEALER("딜러");

    private final String name;

    ReservedRole(final String name) {
        this.name = name;
    }

    public static boolean hasRoleSameNameWith(String otherName) {
        return Arrays.stream(values())
                .anyMatch(reservedRole -> reservedRole.name.equals(otherName));
    }

    public String getName() {
        return name;
    }
}

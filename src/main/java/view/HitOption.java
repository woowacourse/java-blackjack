package view;

import java.util.Arrays;

public enum HitOption {

    HIT("y"),
    STAY("n");

    private final String command;

    HitOption(String command) {
        this.command = command;
    }

    public static HitOption from(String inputOption) {
        return Arrays.stream(values())
                .filter(hitOption -> hitOption.command.equalsIgnoreCase(inputOption))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y나 n 둘 중 하나로 입력할 수 있습니다."));
    }

    public boolean isStayOption() {
        return this == STAY;
    }
}

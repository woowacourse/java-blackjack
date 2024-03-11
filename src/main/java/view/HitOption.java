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
                .filter(hitOption -> hitOption.command.equals(inputOption.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y나 n 둘 중 하나로 입력할 수 있습니다."));
    }

    public static boolean isStayOption(String hitOption) {
        HitOption findOption = from(hitOption);
        return STAY == findOption;
    }
}

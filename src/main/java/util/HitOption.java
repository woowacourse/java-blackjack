package util;

import java.util.Arrays;

public enum HitOption {
    YES("y"), NO("n");

    private final String value;

    HitOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HitOption of(String value) {
        String lowerCaseValue = value.toLowerCase();
        return Arrays.stream(HitOption.values())
                .filter(hitOption -> lowerCaseValue.equals(hitOption.getValue()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력은 y 또는 n만 가능합니다."));
    }
}

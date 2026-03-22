package domain;

import java.util.Arrays;

public enum HitOption {
    HIT("y"),
    STAY("n");
    
    private final String value;
    
    HitOption(String value) {
        this.value = value;
    }
    
    public static HitOption from(String input) {
        return Arrays.stream(values())
                .filter(option -> option.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] y 또는 n을 입력해주세요."));
    }
    
    public boolean isStay() {
        return this == STAY;
    }
}

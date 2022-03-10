package blackjack.domain;

import java.util.Arrays;

public enum HitRequest {
    YES("y", true),
    NO("n", false);

    private final String name;
    private final boolean value;

    HitRequest(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public static HitRequest find(String requestName) {
        return Arrays.stream(HitRequest.values())
                .filter(value -> value.getName().equals(requestName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 요청입니다."));
    }

    public String getName() {
        return name;
    }

    public boolean isValue() {
        return value;
    }
}

package blackjack.domain;

import java.util.Arrays;

public enum DrawStatus {

    YES("y"),
    NO("n");

    private final String value;

    DrawStatus(String value) {
        this.value = value;
    }

    public static DrawStatus from(String inputResponse) {
        return Arrays.stream(values())
                .filter(drawStatus -> drawStatus.getValue().equals(inputResponse))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y또는 n 응답으로 입력해주세요."));
    }

    public String getValue() {
        return value;
    }
}

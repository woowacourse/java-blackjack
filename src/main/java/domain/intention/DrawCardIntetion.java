package domain.intention;

import java.util.Arrays;

public enum DrawCardIntetion {

    YES("y"),
    NO("n");

    private final String intention;

    DrawCardIntetion(String intention) {
        this.intention = intention;
    }

    public static DrawCardIntetion from(String value) {
        return Arrays.stream(DrawCardIntetion.values())
                .filter(v -> v.intention.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요.")
                );
    }

    public boolean isYes() {
        return this.equals(DrawCardIntetion.YES);
    }

}

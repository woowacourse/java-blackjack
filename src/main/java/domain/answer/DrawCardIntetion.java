package domain.answer;

import java.util.Arrays;

public enum DrawCardIntetion {

    YES("y"),
    NO("n")
    ;

    private final String answer;

    DrawCardIntetion(String ansewr) {
        this.answer = ansewr;
    }

    public static DrawCardIntetion from(String value) {
        return Arrays.stream(DrawCardIntetion.values())
                .filter(v -> v.answer.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요.")
                );
    }

    public boolean isNo() {
        return this.equals(DrawCardIntetion.NO);
    }

}

package blackjack.view;

import java.util.Arrays;

public enum Intention {

    YES("y", true),
    NO("n", false);

    private final String value;
    private final boolean flag;

    Intention(String value, boolean flag) {
        this.value = value;
        this.flag = flag;
    }

    public static boolean getIntention(String inputIntention) {
        return Arrays.stream(values())
                .filter(intention -> intention.value.equals(inputIntention))
                .map(intention -> intention.flag)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n만 입력 가능합니다."));
    }

}

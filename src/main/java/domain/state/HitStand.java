package domain.state;

import java.util.Arrays;

public enum HitStand {
    HIT("y"), STAND("n");

    private final String answer;

    HitStand(String answer) {
        this.answer = answer;
    }

    public static boolean validate(String input) {
        return Arrays.stream(values())
                .anyMatch(value -> value.answer.equals(input));
    }

    public static boolean isHit(String input){
        return HIT.answer.equals(input);
    }
}

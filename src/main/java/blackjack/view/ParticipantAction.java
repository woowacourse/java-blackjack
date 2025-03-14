package blackjack.view;

import java.util.Arrays;

public enum ParticipantAction {

    HIT("y"),
    STAND("n");

    private final String inputValue;

    ParticipantAction(String inputValue) {
        this.inputValue = inputValue;
    }

    public boolean isHit() {
        return this == HIT;
    }

    public static ParticipantAction from(String input) {
        return Arrays.stream(values())
                .filter(action -> action.inputValue.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 입력이 아닙니다. 입력: %s".formatted(input)));
    }
}

package domain.game;

import java.util.Arrays;

public enum WhetherAddCard {
    YES("y"),
    NO("n");

    private final String wantToAdd;

    WhetherAddCard(final String wantToAdd) {
        this.wantToAdd = wantToAdd;
    }

    public static WhetherAddCard of(final String wantToAdd) {
        return Arrays.stream(values())
                .filter(whetherAddCard -> whetherAddCard.wantToAdd.equals(wantToAdd))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n이 아닙니다."));
    }

    public boolean isYes() {
        return this == YES;
    }
}

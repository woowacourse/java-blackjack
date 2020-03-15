package domain.game;

import java.util.Arrays;

public enum UserIntention {
    YES("y"),
    NO("n");

    private final String wantToAdd;

    UserIntention(final String wantToAdd) {
        this.wantToAdd = wantToAdd;
    }

    public static UserIntention of(final String wantToAdd) {
        return Arrays.stream(values())
                .filter(userIntention -> userIntention.wantToAdd.equals(wantToAdd))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n이 아닙니다."));
    }

    public boolean isYes() {
        return this == YES;
    }
}

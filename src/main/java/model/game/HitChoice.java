package model.game;

public enum HitChoice {
    YES("y"),
    NO("n");

    private final String displayName;

    HitChoice(String displayName) {
        this.displayName = displayName;
    }

    public static HitChoice findHitChoice(String choice) {
        if (YES.displayName.equals(choice)) {
            return YES;
        }
        if (NO.displayName.equals(choice)) {
            return NO;
        }
        throw new IllegalArgumentException("y 혹은 n 중 하나를 입력해 주세요.");
    }

    public boolean isHit() {
        return this == YES;
    }
}

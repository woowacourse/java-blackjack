package domain.blackjack;

public enum HitOption {
    YES("y"),
    NO("n");

    private final String hitOption;

    HitOption(String hitOption) {
        this.hitOption = hitOption;
    }

    public static HitOption from(String option) {
        if (YES.hitOption.equals(option)) {
            return YES;
        }
        if (NO.hitOption.equals(option)) {
            return NO;
        }
        throw new IllegalArgumentException("y 또는 n 중 하나만 입력 가능합니다.");
    }

    public static boolean isHit(HitOption isHit) {
        return isHit == YES;
    }
}

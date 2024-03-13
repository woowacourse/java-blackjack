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
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    public static boolean isHit(HitOption isHit) {
        if (isHit == YES) {
            return true;
        }
        return false;
    }
}

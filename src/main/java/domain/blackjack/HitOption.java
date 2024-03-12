package domain.blackjack;

public enum HitOption {
    YES("y"),
    NO("n");

    private final String hitOption;

    HitOption(String hitOption) {
        this.hitOption = hitOption;
    }

    public static boolean isHit(String option) {
        if (YES.hitOption.equals(option)) {
            return true;
        }
        if (NO.hitOption.equals(option)) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}

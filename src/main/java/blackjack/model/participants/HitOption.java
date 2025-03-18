package blackjack.model.participants;

public enum HitOption {
    YES("y"),
    NO("n");

    private static final String INVALID_OPTION = "[ERROR] 존재하지 않는 옵션 값입니다.";

    private final String option;

    HitOption(String option) {
        this.option = option;
    }

    public static boolean isNo(String option) {
        if (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n")) {
            throw new IllegalArgumentException(INVALID_OPTION);
        }
        return option.equalsIgnoreCase(NO.option);
    }
}

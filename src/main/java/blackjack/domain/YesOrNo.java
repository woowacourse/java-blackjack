package blackjack.domain;

public enum YesOrNo {
    YES, NO;

    public static YesOrNo from(final String input) {
        if (input.equalsIgnoreCase("y")) {
            return YesOrNo.YES;
        }

        if (input.equalsIgnoreCase("n")) {
            return YesOrNo.NO;
        }

        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
    }

    public static boolean isYes(final String input) {
        YesOrNo yesOrNo = YesOrNo.from(input);
        return yesOrNo.equals(YesOrNo.YES);
    }
}

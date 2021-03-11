package blackjack.domain;

public enum YesOrNo {
    YES, NO;

    public static YesOrNo of(final String input) {
        if (input.equalsIgnoreCase("y")) {
            return YesOrNo.YES;
        }

        if (input.equalsIgnoreCase("n")) {
            return YesOrNo.NO;
        }

        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
    }

    public boolean isYes() {
        return this.equals(YesOrNo.YES);
    }
}

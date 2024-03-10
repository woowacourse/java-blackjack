package domain.blackjackgame;

public enum BlackjackAction {
    HIT("y"),
    STAY("n");

    private final String expression;

    BlackjackAction(String expression) {
        this.expression = expression;
    }

    public static BlackjackAction from(String expression) {
        if (HIT.expression.equals(expression)) {
            return HIT;
        }
        if (STAY.expression.equals(expression)) {
            return STAY;
        }
        throw new IllegalArgumentException("잘못된 입력 값입니다.");
    }

    public boolean isHit() {
        return this == HIT;
    }
}

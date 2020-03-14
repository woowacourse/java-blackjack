package blackjack.domain.rule;

public enum PlayerAnswer {

    Y,
    N;

    public static PlayerAnswer of(String answer) {
        return valueOf(answer.toUpperCase());
    }

    public boolean isYes() {
        return this == Y;
    }

    public boolean isNo() {
        return this == N;
    }
}


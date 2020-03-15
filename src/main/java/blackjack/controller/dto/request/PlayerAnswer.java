package blackjack.controller.dto.request;

public enum PlayerAnswer {

    Y,
    N;

    public static PlayerAnswer of(String answer) {
        return valueOf(answer.toUpperCase());
    }

    public boolean isYes() {
        return this == Y;
    }
}


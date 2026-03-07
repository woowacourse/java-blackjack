package blackjack.domain;

public enum MatchResult {

    WIN,
    DRAW,
    LOSE;

    public static MatchResult reverse(MatchResult matchResult) {
        if (matchResult == WIN) return LOSE;
        if (matchResult == LOSE) return WIN;

        return DRAW;
    }
}

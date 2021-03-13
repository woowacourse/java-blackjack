package blackjack.domain.user;

public enum MatchResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    MatchResult(String name) {
        this.name = name;
    }

    public static MatchResult calculateResult(User player, User dealer) {
        if (player.isSameStatus(HandStatus.BUST)) {
            return MatchResult.LOSE;
        }

        if (player.isSameStatus(HandStatus.BLACK_JACK)) {
            return isDealerBlackJack(dealer);
        }

        if (dealer.isSameStatus(HandStatus.BUST)) {
            return MatchResult.WIN;
        }

        return compareScore(player, dealer);
    }

    private static MatchResult isDealerBlackJack(User dealer) {
        if (dealer.isSameStatus(HandStatus.BLACK_JACK)) {
            return MatchResult.DRAW;
        }
        return MatchResult.WIN;
    }

    private static MatchResult compareScore(User player, User dealer) {
        if (player.isLessScoreThan(dealer)) {
            return MatchResult.LOSE;
        }

        if (player.isSameScore(dealer)) {
            return MatchResult.DRAW;
        }

        return MatchResult.WIN;
    }

    public String getName() {
        return name;
    }

    public String getReverseName() {
        if (this == MatchResult.WIN) {
            return "패";
        }

        if (this == MatchResult.LOSE) {
            return "승";
        }

        return "무";
    }
}

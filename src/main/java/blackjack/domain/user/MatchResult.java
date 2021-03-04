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
        if (player.getStatus() == HandStatus.BUST) {
            return MatchResult.LOSE;
        }

        if (player.getStatus() == HandStatus.BLACK_JACK) {
            return isDealerBlackJack(dealer.getStatus());
        }

        if (dealer.getStatus() == HandStatus.BUST) {
            return MatchResult.WIN;
        }

        return compareScore(player.getScore(), dealer.getScore());
    }

    private static MatchResult isDealerBlackJack(HandStatus dealerStatus) {
        if (dealerStatus == HandStatus.BLACK_JACK) {
            return MatchResult.DRAW;
        }
        return MatchResult.WIN;
    }

    private static MatchResult compareScore(int playerScore, int dealerScore) {
        if (playerScore < dealerScore) {
            return MatchResult.LOSE;
        }

        if (playerScore == dealerScore) {
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

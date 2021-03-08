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
        if (player.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return MatchResult.DRAW;
        }
        return compareScore(player.getScore(), dealer.getScore());
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

    public String getReverseName() {
        if(this == MatchResult.WIN) {
            return LOSE.name;
        }
        if(this == MatchResult.LOSE) {
            return WIN.name;
        }
        return DRAW.name;
    }

    public String getName() {
        return name;
    }
}

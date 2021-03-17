package blackjack.domain.user;

public enum MatchResult {
    BLACKJACK_WIN("블랙잭", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private final String name;
    private final double moneyRate;

    MatchResult(String name, double moneyRate) {
        this.name = name;
        this.moneyRate = moneyRate;
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
        return MatchResult.BLACKJACK_WIN;
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

    public double getMoneyRate() {
        return moneyRate;
    }
}

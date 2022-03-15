package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Outcome {
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    WIN_BLACKJACK("승", 1.5);

    private final String name;
    private final double earningsRate;

    Outcome(String name, double earningsRate) {
        this.name = name;
        this.earningsRate = earningsRate;
    }

    public static Outcome judge(Player player, Dealer dealer) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return WIN_BLACKJACK;
        }
        if (player.isBust() || !player.isBlackjack() && dealer.isBlackjack()) {
            return Outcome.LOSE;
        }
        if (dealer.isBust() || player.isBlackjack() && !dealer.isBlackjack()) {
            return Outcome.WIN;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Outcome.DRAW;
        }
        return judgeByScore(player.getScore(), dealer.getScore());
    }

    private static Outcome judgeByScore(Score score, Score target) {
        if (score.compareTo(target) > 0) {
            return Outcome.WIN;
        }
        if (score.equals(target)) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }

    public Outcome getOpposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }

    public double getEarningsRate() {
        return earningsRate;
    }
}

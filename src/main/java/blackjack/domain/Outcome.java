package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum Outcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Outcome(String name) {
        this.name = name;
    }

    public static Outcome judge(Player player, Dealer dealer) {
        if (player.isBust() || !player.isBlackJack() && dealer.isBlackJack()) {
            return Outcome.LOSE;
        }
        if (dealer.isBust() || player.isBlackJack() && !dealer.isBlackJack()) {
            return Outcome.WIN;
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
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
}

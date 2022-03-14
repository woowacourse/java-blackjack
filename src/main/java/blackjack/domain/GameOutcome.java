package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum GameOutcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String printValue;

    GameOutcome(final String printValue) {
        this.printValue = printValue;
    }

    public static GameOutcome calculateOutcome(final Player player, final Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (player.isBlackJack()) {
            return playerInBlackJack(dealer);
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        return calculateOutcome(player.calculateResultScore(), dealer.calculateResultScore());
    }

    private static GameOutcome playerInBlackJack(final Dealer dealer) {
        if (dealer.isBlackJack()) {
            return DRAW;
        }
        return WIN;
    }

    private static GameOutcome calculateOutcome(final int score, final int compareScore) {
        if (score > compareScore) {
            return WIN;
        }
        if (score < compareScore) {
            return LOSE;
        }
        return DRAW;
    }

    public GameOutcome reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getPrintValue() {
        return printValue;
    }
}

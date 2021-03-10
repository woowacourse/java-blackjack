package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;

public enum Outcome {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String word;

    Outcome(String word) {
        this.word = word;
    }

    public static Outcome getInstance(final Score base, final Score counterpart) {
        if (win(base, counterpart)) {
            return Outcome.LOSE;
        }

        if (draw(base, counterpart)) {
            return Outcome.WIN;
        }

        return Outcome.LOSE;
    }

    private static boolean draw(final Score base, final Score counterpart) {
        if (base.isBurst() && counterpart.isBurst()) {
            return true;
        }
        if (!base.isBurst() && !counterpart.isBurst() && base.isSameAs(counterpart) ) {
            return true;
        }
        return false;
    }

    private static boolean win(final Score base, final Score counterpart) {
        if (base.isBlackJack() && !counterpart.isBlackJack()) {
            return true;
        }
        if (!base.isBurst() && base.isHigherThan(counterpart)) {
            return true;
        }
        return false;
    }
}

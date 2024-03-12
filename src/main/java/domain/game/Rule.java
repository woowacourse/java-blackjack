package domain.game;

import domain.card.Cards;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.score.Status;

import static domain.score.Status.BLACKJACK;
import static domain.score.Status.TIE;

public class Rule {

    private static final int STANDARD = 21;

    public Rule() {
    }

    public Status decideDealerStatus(DealerCards dealer) {
        if (isBust(dealer)) {
            return Status.LOSE;
        }
        if (isBlackJack(dealer)) {
            return Status.BLACKJACK;
        }
        return Status.TIE;
    }

    public Status decidePlayerBlackjackStatus(Status dealerStatus) {
        if (dealerStatus == BLACKJACK) {
            return TIE;
        }
        return BLACKJACK;
    }

    public Status decidePlayerStatus(int dealerSum, PlayerCards playerCards) {
        if (dealerSum < playerCards.bestSum()) {
            return Status.WIN;
        }

        if (dealerSum > playerCards.bestSum()) {
            return Status.LOSE;
        }

        return Status.TIE;
    }

    public boolean isBlackJack(Cards cards) {
        return cards.sumInitCards() == STANDARD;
    }

    public boolean isBust(Cards cards) {
        return cards.bestSum() > STANDARD;
    }
}

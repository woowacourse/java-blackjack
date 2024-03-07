package blackjack.domain;

import blackjack.domain.card.Cards;

public class Referee {

    private final Cards dealerCards;

    public Referee(Cards dealerCards) {
        this.dealerCards = dealerCards;
    }

    public Outcome doesPlayerWin(Cards playerCards) {
        if (dealerCards.sum() < playerCards.sum()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }
}

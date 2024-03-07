package blackjack.domain;

import blackjack.domain.card.Cards;

public class Referee {

    private static final int BLACKJACK_CANDIDATE = 21;

    private final Cards dealerCards;

    public Referee(Cards dealerCards) {
        this.dealerCards = dealerCards;
    }

    public Outcome doesPlayerWin(Cards playerCards) {
        if (playerCards.sum() > BLACKJACK_CANDIDATE) {
            return calculatePlayerBustCase();
        }
        if (playerCards.sum() == BLACKJACK_CANDIDATE && playerCards.hasOnlyInitialCard()) {
            return calculatePlayerBlackJackCase();
        }
        return calculateNormalCase(playerCards);
    }

    private Outcome calculatePlayerBustCase() {
        if (dealerCards.sum() > BLACKJACK_CANDIDATE) {
            return Outcome.PUSH;
        }
        return Outcome.LOSE;
    }

    private Outcome calculatePlayerBlackJackCase() {
        if (dealerCards.sum() == BLACKJACK_CANDIDATE && dealerCards.hasOnlyInitialCard()) {
            return Outcome.PUSH;
        }
        return Outcome.WIN;
    }

    private Outcome calculateNormalCase(final Cards playerCards) {
        if (dealerCards.sum() < playerCards.sum()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }
}

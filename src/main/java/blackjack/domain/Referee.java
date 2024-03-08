package blackjack.domain;

import blackjack.domain.card.Cards;

public class Referee {

    private static final int BLACKJACK_CANDIDATE = 21;

    private final Cards dealerCards;

    public Referee(Cards dealerCards) {
        this.dealerCards = dealerCards;
    }

    public Outcome doesPlayerWin(final Cards playerCards) {
        if (isBust(playerCards.sum()) || isBust(dealerCards.sum())) {
            return calculateBustCase(playerCards.sum());
        }
        if (isBlackJack(dealerCards) || isBlackJack(playerCards)) {
            return calculateBlackJackCase(playerCards);
        }
        return calculateNormalCase(playerCards);
    }

    private boolean isBust(int score) {
        return score > BLACKJACK_CANDIDATE;
    }

    private Outcome calculateBustCase(final int playerScore) {
        if (isBust(dealerCards.sum()) && isBust(playerScore)) {
            return Outcome.PUSH;
        }
        if (isBust(dealerCards.sum())) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private boolean isBlackJack(Cards cards) {
        return cards.sum() == BLACKJACK_CANDIDATE && cards.hasOnlyInitialCard();
    }

    private Outcome calculateBlackJackCase(final Cards playerCards) {
        if (isBlackJack(dealerCards) && isBlackJack(playerCards)) {
            return Outcome.PUSH;
        }
        if (isBlackJack(dealerCards)) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private Outcome calculateNormalCase(final Cards playerCards) {
        if (dealerCards.sum() < playerCards.sum()) {
            return Outcome.WIN;
        }
        if (dealerCards.sum() > playerCards.sum()) {
            return Outcome.LOSE;
        }
        return Outcome.PUSH;
    }
}

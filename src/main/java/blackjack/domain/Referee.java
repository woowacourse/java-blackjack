package blackjack.domain;

import blackjack.domain.card.Hand;

public class Referee {

    private final Hand dealerHand;

    public Referee(final Hand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public Outcome doesPlayerWin(final Hand playerHand) {
        if (isBust(dealerHand) || isBust(playerHand)) {
            return calculateBustCase(playerHand);
        }
        if (isBlackJack(dealerHand) || isBlackJack(playerHand)) {
            return calculateBlackJackCase(playerHand);
        }
        return calculateNormalCase(playerHand);
    }

    private boolean isBust(final Hand hand) {
        return hand.isBust();
    }

    private Outcome calculateBustCase(final Hand playerHand) {
        if (isBust(dealerHand) && isBust(playerHand)) {
            return Outcome.PUSH;
        }
        if (isBust(dealerHand)) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private boolean isBlackJack(final Hand hand) {
        return hand.isBlackJack();
    }

    private Outcome calculateBlackJackCase(final Hand playerHand) {
        if (isBlackJack(dealerHand) && isBlackJack(playerHand)) {
            return Outcome.PUSH;
        }
        if (isBlackJack(dealerHand)) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private Outcome calculateNormalCase(final Hand playerHand) {
        if (dealerHand.sum() < playerHand.sum()) {
            return Outcome.WIN;
        }
        if (dealerHand.sum() > playerHand.sum()) {
            return Outcome.LOSE;
        }
        return Outcome.PUSH;
    }
}

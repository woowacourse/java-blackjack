package blackjack.domain;

import blackjack.domain.card.Hand;

public class Referee {

    private static final int BLACKJACK_CANDIDATE = 21;

    private final Hand dealerHand;

    public Referee(final Hand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public Outcome doesPlayerWin(final Hand playerHand) {
        if (isBust(playerHand.sum()) || isBust(dealerHand.sum())) {
            return calculateBustCase(playerHand.sum());
        }
        if (isBlackJack(dealerHand) || isBlackJack(playerHand)) {
            return calculateBlackJackCase(playerHand);
        }
        return calculateNormalCase(playerHand);
    }

    private boolean isBust(final int score) {
        return score > BLACKJACK_CANDIDATE;
    }

    private Outcome calculateBustCase(final int playerScore) {
        if (isBust(dealerHand.sum()) && isBust(playerScore)) {
            return Outcome.PUSH;
        }
        if (isBust(dealerHand.sum())) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private boolean isBlackJack(final Hand hand) {
        return hand.sum() == BLACKJACK_CANDIDATE && hand.hasOnlyInitialCard();
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

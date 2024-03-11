package blackjack.domain;

import blackjack.domain.card.Cards;

public class Referee {

    private static final int BLACKJACK_CANDIDATE = 21;

    private final Cards dealerCards;

    public Referee(Cards dealerCards) {
        this.dealerCards = dealerCards;
    }

    public Outcome doesPlayerWin(final Cards playerCards) {
        if (isBust(playerCards.calculateOptimalSum()) || isBust(dealerCards.calculateOptimalSum())) {
            return calculateBustCase(playerCards.calculateOptimalSum());
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
        if (isBust(dealerCards.calculateOptimalSum()) && isBust(playerScore)) {
            return Outcome.PUSH;
        }
        if (isBust(dealerCards.calculateOptimalSum())) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private boolean isBlackJack(Cards cards) {
        return cards.calculateOptimalSum() == BLACKJACK_CANDIDATE && cards.hasOnlyInitialCard();
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
        if (dealerCards.calculateOptimalSum() < playerCards.calculateOptimalSum()) {
            return Outcome.WIN;
        }
        if (dealerCards.calculateOptimalSum() > playerCards.calculateOptimalSum()) {
            return Outcome.LOSE;
        }
        return Outcome.PUSH;
    }
}

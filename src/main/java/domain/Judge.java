package domain;

import domain.card.Cards;

public class Judge {

    public static GameState gameResult(Cards dealerCards, Cards playerCards) {
        if (playerCards.isBurst() || dealerCards.isHigherThan(playerCards)) {
            return GameState.LOSE;
        }
        if (dealerCards.isBurst() || playerCards.isHigherThan(dealerCards)) {
            return checkBlackJackState(playerCards);
        }
        return GameState.DRAW;
    }

    private static GameState checkBlackJackState(Cards cards) {
        if (cards.isBlackJack()) {
            return GameState.BLACKJACK;
        }
        return GameState.WIN;
    }
}

package domain.gamer;

import domain.card.Cards;

public class Referee {

    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final int BUST_SCORE = 0;

    public boolean isBlackJack(Cards cards) {
        return cards.countMaxScore() == BLACKJACK_SCORE && cards.getSize() == BLACKJACK_CARD_SIZE;
    }

    public PlayerResult judgeResult(Cards playerCards, Cards dealerCards) {
        if (isBlackJack(playerCards) && isBlackJack(dealerCards)) {
            return PlayerResult.DRAW;
        }
        if (isBlackJack(playerCards) && !isBlackJack(dealerCards)) {
            return PlayerResult.BLACKJACKWIN;
        }
        return notBlackJackResult(playerCards, dealerCards);
    }

    private static PlayerResult notBlackJackResult(Cards playerCards, Cards dealerCards) {
        if (playerCards.countMaxScore() == BUST_SCORE) {
            return PlayerResult.LOSE;
        }
        if (playerCards.countMaxScore() > dealerCards.countMaxScore()) {
            return PlayerResult.WIN;
        }
        if (playerCards.countMaxScore() == dealerCards.countMaxScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}

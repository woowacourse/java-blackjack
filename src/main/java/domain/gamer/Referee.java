package domain.gamer;

import domain.card.Cards;

public class Referee {
    public boolean isBlackJack(Cards cards) {
        if (cards.countMaxScore() == 21 && cards.getSize() == 2) {
            return true;
        }
        return false;
    }

    public PlayerResult judgeResult(Cards playerCards, Cards dealerCards) {
        if (isBlackJack(playerCards)) {
            if (!isBlackJack(dealerCards)) {
                return PlayerResult.BLACKJACKWIN;
            }
            return PlayerResult.DRAW;
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

package domain.gamer;

import domain.card.Cards;

public class Referee {
    public boolean isBlackJack(Cards cards) {
        return cards.countMaxScore() == 21 && cards.getSize() == 2;
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
        if (playerCards.countMaxScore() > dealerCards.countMaxScore()) {
            return PlayerResult.WIN;
        }
        if (playerCards.countMaxScore() == dealerCards.countMaxScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}

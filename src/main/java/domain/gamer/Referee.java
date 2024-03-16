package domain.gamer;

import domain.card.Cards;

public class Referee {
    public boolean isBlackJack(Cards cards) {
        if (cards.countMaxScore() == 21 && cards.getSize() == 2) {
            return true;
        }
        return false;
    }
}

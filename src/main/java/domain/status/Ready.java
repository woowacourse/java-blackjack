package domain.status;

import domain.card.Cards;
import domain.participant.Score;

public class Ready implements Status {
    public Status firstDraw(final Cards cards) {
        Score score = cards.calculateScore();
        if (score.isBlackJack()) {
            return new BlackJack();
        }
        return new Hit(cards);
    }
}

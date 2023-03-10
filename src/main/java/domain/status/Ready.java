package domain.status;

import domain.card.Cards;
import domain.participant.Score;

public class Ready extends Status {
    public Status firstDraw(final Cards cards) {
        Score score = cards.calculateScore();
        if (score.isBlackJack()) {
            return new BlackJack();
        }
        return new Hit(cards);
    }
}

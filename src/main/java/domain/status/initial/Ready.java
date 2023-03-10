package domain.status.initial;

import domain.card.Cards;
import domain.participant.Score;
import domain.status.Status;
import domain.status.end.BlackJack;
import domain.status.intermediate.Hit;

public class Ready implements Status {
    public Status firstDraw(final Cards cards) {
        Score score = cards.calculateScore();
        if (score.isBlackJack()) {
            return new BlackJack();
        }
        return new Hit(cards);
    }
}

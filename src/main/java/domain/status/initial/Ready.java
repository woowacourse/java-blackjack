package domain.status.initial;

import domain.card.Cards;
import domain.score.Score;
import domain.status.Status;
import domain.status.end.BlackJack;
import domain.status.intermediate.Hit;

public final class Ready extends InitialStatus {

    @Override
    public Status initialDraw(final Cards cards) {
        Score score = cards.calculateScore();
        if (score.isMax()) {
            return new BlackJack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public Cards cards() {
        return new Cards();
    }
}

package domain.gamer;

import domain.card.Card;
import domain.card.cardDrawable.Hand;
import domain.result.Score;

import java.util.List;

import static domain.result.ScoreCalculable.BLACK_JACK_SCORE;

public class Player extends AbstractGamer {
    public Player(String name) {
        super(name, new Hand());
    }

    @Override
    public boolean canDrawMore() {
        return calculateScore().isLowOrEqualThan(new Score(BLACK_JACK_SCORE));
    }

    @Override
    public List<Card> showInitialCards() {
        return super.showAllCards();
    }
}

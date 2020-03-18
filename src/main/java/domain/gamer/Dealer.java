package domain.gamer;

import domain.card.Card;
import domain.card.cardDrawable.Hand;
import domain.result.Score;

import java.util.Arrays;
import java.util.List;

import static domain.result.ScoreCalculable.DEALER_DRAW_THRESHOLD;

public class Dealer extends AbstractGamer {
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME, new Hand());
    }

    @Override
    public boolean canDrawMore() {
        return calculateScore().isLowOrEqualThan(new Score(DEALER_DRAW_THRESHOLD));
    }

    @Override
    public List<Card> openInitialCards() {
        return Arrays.asList(hand.getOneCard());
    }
}

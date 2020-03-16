package domain.gamer;

import domain.card.Card;
import domain.card.cardDrawable.Hand;
import domain.result.Score;
import domain.result.WinLose;

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
    public List<Card> showInitialCards() {
        return Arrays.asList(hand.getOneCard());
    }

    @Override
    public WinLose determineWinLose(Gamer counterParts) {
        if (WinLose.determineWinner(counterParts, this) == this) {
            return WinLose.WIN;
        }

        return WinLose.LOSE;
    }
}

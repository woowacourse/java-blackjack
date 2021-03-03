package blackjack.domain.player;

import blackjack.util.BlackjackScoreCalculator;
import blackjack.util.ScoreCalculator;

public class Dealer extends Player {

    private static final int DRAWABLE_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(ScoreCalculator scoreCalculator) {
        super(DEALER_NAME, scoreCalculator);
    }

    public Dealer() {
        this(new BlackjackScoreCalculator());
    }

    @Override
    public boolean isDrawable() {
        return getScore() < DRAWABLE_THRESHOLD;
    }


}

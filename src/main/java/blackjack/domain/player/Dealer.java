package blackjack.domain.player;

import blackjack.domain.Name;
import blackjack.domain.card.GamblerCards;
import java.util.List;

public final class Dealer extends Player implements Drawable {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int BASES_SCORE_CAN_DRAW = 16;
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 1;

    public Dealer() {
        this(new GamblerCards());
    }

    public Dealer(GamblerCards gamblerCards) {
        super(DEALER_NAME, gamblerCards);
    }

    @Override
    public boolean canDraw() {
        return isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }

    @Override
    public List<String> getFirstTimeCardsInfo() {
        return getCardsInfos().subList(FROM_INDEX, TO_INDEX);
    }
}

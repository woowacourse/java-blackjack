package blackjack.domain.player;

import blackjack.domain.card.Hands;
import blackjack.domain.rule.Score;
import java.util.List;

public class Dealer extends User {

    public static final Score NEED_CARD_SCORE_MAX = Score.from(16);
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new UserName(DEALER_NAME));
    }

    @Override
    public boolean canHit() {
        final Score score = getHands().calculateScore();

        return score.compareTo(NEED_CARD_SCORE_MAX) <= 0;
    }

    @Override
    public Hands getOpenedHands() {
        return new Hands(List.of(getHands().getFirstCard()));
    }
}

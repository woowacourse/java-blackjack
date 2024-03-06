package blackjack.domain.participant;

import blackjack.domain.Card;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int DRAWABLE_MAX_SCORE = 16;

    public Dealer() {
        super(Collections.emptyList());
    }

    Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    protected int getMaxDrawableScore() {
        return DRAWABLE_MAX_SCORE;
    }

    public boolean isWin(Player player) {
        if (player.isBusted()) {
            return true;
        }
        if (this.isBusted()) {
            return false;
        }
        return this.calculateScore() >= player.calculateScore();
    }
}

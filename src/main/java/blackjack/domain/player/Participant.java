package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Participant extends Player {

    private boolean winState = false;

    public Participant(final List<Card> cards, final String name) {
        super(cards, name);
    }

    public void win() {
        this.winState = true;
    }

    public boolean isOverMaxScore() {
        return getScoreByAceOne() > MAX_SCORE;
    }

    public boolean getWinState() {
        return this.winState;
    }
}

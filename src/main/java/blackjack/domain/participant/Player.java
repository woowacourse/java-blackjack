package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Player extends Participant {
    private boolean win = true;

    public Player(final String name) {
        super(name);
    }

    public void lose() {
        this.win = false;
    }

    public boolean getWin() {
        return win;
    }

    @Override
    public List<Card> showInitialCards() {
        return cards.subList(0, 2);
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return !isBust();
    }
}

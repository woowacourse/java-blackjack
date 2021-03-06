package blackjack.domain.participants;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    private static final int BUST_LIMIT = 22;
    private boolean win = true;

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> showCards() {
        return new ArrayList<>(getPlayerCards());
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return !isBust();
    }

    public void lose() {
        win = false;
    }

    public boolean getWin() {
        return win;
    }
}

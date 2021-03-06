package blackjack.domain.participants;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private boolean win = true;

    public Player(String name) {
        super(name);
    }

    @Override
    List<Card> pickCards() {
        return getPlayerCards();
    }

    public void lose() {
        win = false;
    }

    public boolean getWin() {
        return win;
    }
}

package blackjack.domain.participants;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isWinner(Participant dealer) {
        return !dealer.isWinner(this);
    }

    @Override
    public List<Card> showCards() {
        return new ArrayList<>(getPlayerCards());
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return !isBust();
    }

}

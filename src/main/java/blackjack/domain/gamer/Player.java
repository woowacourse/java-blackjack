package blackjack.domain.gamer;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends BlackjackGamer {

    public Player(String name) {
        super(name);
    }

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean canReceiveCard() {
        return getScore() <= BlackjackConstants.BLACKJACK_VALUE.getValue();
    }
}

package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public class User extends Player {

    public User(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean isPossibleToPickCard() {
        return cards.calculateScore() <= 21;
    }
}

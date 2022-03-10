package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public class User extends Player {

    private static final int MAX_SCORE_TO_PICK = 21;

    public User(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean isPossibleToPickCard() {
        return cards.calculateScore() <= MAX_SCORE_TO_PICK;
    }
}

package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.strategy.HitStrategy;

import java.util.Collections;
import java.util.List;

public class Player extends User {

    private static final int INIT_COUNT = 2;

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> showInitCards() {
        return Collections.unmodifiableList(cards.subList(0, INIT_COUNT));
    }
}

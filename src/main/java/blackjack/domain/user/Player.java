package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.Collections;
import java.util.List;

public class Player extends User {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int INIT_COUNT = 2;

    private final String name;

    public Player(String name) {
        super();
        this.name = name;
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
    }

    public List<Card> showInitCards() {
        return Collections.unmodifiableList(cards.subList(0, INIT_COUNT));
    }

    public boolean isDrawable() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum() < BLACKJACK_NUMBER ;
    }
}

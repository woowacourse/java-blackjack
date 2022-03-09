package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
    }

    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }
}

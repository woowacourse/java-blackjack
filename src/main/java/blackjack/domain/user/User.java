package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {

    protected final List<Card> cards;
    protected final String name;

    protected User(String name) {
        cards = new ArrayList<>();
        this.name = name;
    }

    public void drawInitCards(Deck deck) {
        cards.add(deck.drawCard());
        cards.add(deck.drawCard());
    }

    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
    }

    public abstract List<Card> showInitCards();

    public abstract boolean isDrawable();

    public String getName(){
        return name;
    }
}

package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;

import java.util.List;

public abstract class User implements Comparable<User> {

    protected final Cards cards;
    protected final Name name;

    protected User(String name) {
        cards = new Cards();
        this.name = new Name(name);
    }

    public void draw(Deck deck) {
        cards.add(deck.dealOut());
    }

    public abstract boolean isAvailableToDraw();

    public boolean isBust() {
        return cards.areBust();
    }

    public boolean isBlackJack() {
        return cards.areBlackJack();
    }

    @Override
    public int compareTo(User user) {
        return calculatePoint() - user.calculatePoint();
    }

    public int calculatePoint() {
        return cards.calculatePoint();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}

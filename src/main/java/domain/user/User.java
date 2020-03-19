package domain.user;

import java.util.List;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;

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
        return cards.calculatePointAccordingToHasAce();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    protected abstract boolean isAvailableToDraw();
}

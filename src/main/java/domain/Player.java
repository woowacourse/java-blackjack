package domain;

import java.util.List;

public class Player {
    private final Name name;
    private final Cards cards;

    public Player(String name) {
        this.name = new Name(name);
        cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCard(int index) {
        return cards.getCard(index);
    }

    public boolean isOverBlackJack() {
        return cards.getSum() >= BlackjackGame.BLACK_JACK;
    }

    public Name getName() {
        return name;
    }

    public List<String> getCards() {
        return cards.getCards();
    }

    public int getCardsSum() {
        return cards.getSum();
    }
}

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

    public boolean isOverBlackJack() {
        return cards.isOverBlackJack();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getCardsSum() {
        return cards.getSum();
    }


}

package domain;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean addCard(final Card card) {
        return cards.addCard(card);
    }

    public int sumOfPlayerCards() {
        return cards.sumOfCards();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isNotBurst() {
        return cards.isNotBurst();
    }
}

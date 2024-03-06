package domain;

public class Participant {
    private final Name name;
    private final Cards cards;

    public Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.sumAll();
    }
}

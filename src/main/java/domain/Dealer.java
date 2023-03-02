package domain;

public class Dealer {
    private static final String NAME = "딜러";
    private final Name name;
    private final Cards cards;

    public Dealer() {
        this.name = new Name(NAME);
        cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name.getName();
    }

    public Card getCard(int index) {
        return cards.getCard(index);
    }

}

package domain;

public class Dealer {
    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public void add(Card card){
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}

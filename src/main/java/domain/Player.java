package domain;

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

    public Card getCard(int index){
        return cards.getCard(index);
    }
}

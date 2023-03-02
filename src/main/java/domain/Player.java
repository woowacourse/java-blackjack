package domain;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void pick(Card card) {
        cards.addNewCard(card);
    }

    public Cards getCards() {
        return cards;
    }

    public Name getName() {
        return name;
    }

}

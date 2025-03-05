package domain;

public class Player {
    private final Cards cards;

    private Player(Cards cards) {
        this.cards = cards;
    }

    public static Player init() {
        return new Player(Cards.empty());
    }

    public static Player of(Cards cards) {
        return new Player(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }

    public int getResult() {
        return 0;
    }
}

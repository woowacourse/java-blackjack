package blackjack.model;

public class Player {
    private final String name;
    private final Cards cards;

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(CardGenerator cardGenerator) {
        cards.addCard(cardGenerator.drawCard());
    }

    public void addCards(CardGenerator cardGenerator) {
        cards.addCard(cardGenerator.drawCards());
    }

    public Cards getCards() {
        return cards;
    }
}

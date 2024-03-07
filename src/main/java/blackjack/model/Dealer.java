package blackjack.model;

public class Dealer {
    private static final int STANDARD = 16;

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public void addCard(CardGenerator cardGenerator) {
        if (isScoreLessThanStandard()) {
            cards.addCard(cardGenerator.drawCard());
        }
    }

    public boolean isScoreLessThanStandard() {
        return cards.calculateScore() <= STANDARD;
    }

    public void addCards(CardGenerator cardGenerator) {
        cards.addCard(cardGenerator.drawCards());
    }

    public Cards getCards() {
        return cards;
    }
}

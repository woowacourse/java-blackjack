package domain;

public abstract class User {
    protected Cards cards;

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, 1);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public abstract boolean canHit();

    public Cards getCards() {
        return cards;
    }
}

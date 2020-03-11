package domain;

public abstract class User {
    protected Cards cards;

    public void drawCard(CardDeck cardDeck) {
        cards.add(cardDeck.draw());
    }

    public abstract boolean isAbleDrawCards();
}

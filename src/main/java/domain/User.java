package domain;

public abstract class User {
    protected Cards cards;

    public void drawCard() {
        cards.add(CardDeck.draw());
    }

    public abstract boolean isAbleDrawCards();
}

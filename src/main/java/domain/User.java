package domain;

public abstract class User {
    protected final String name;
    protected final CardDeck cardDeck;

    public User(String name) {
        this.name = name;
        this.cardDeck = new CardDeck();
    }

    public void drawCard() {
        TrumpCard trumpCard = CardSetting.drawCard();
        cardDeck.addTrumpCard(trumpCard);
    }

    public boolean has(String name) {
        return this.name.equals(name);
    }

    public int getSize() {
        return cardDeck.cardsSize();
    }
}

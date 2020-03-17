package domain;

public abstract class User {
    private final Name name;
    protected Cards cards;

    public User(Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, 1);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public abstract boolean canHit();

    public int getScore() {
        return cards.getScore();
    }

    public Cards getCards() {
        return cards;
    }

    public Name getName() {
        return name;
    }
}

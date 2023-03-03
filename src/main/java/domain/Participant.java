package domain;

public abstract class Participant {

    private static final int BUST_LIMIT = 21;

    protected final Name name;
    protected final CardDeck cardDeck;

    protected Participant(Name name, CardDeck cardDeck) {
        this.name = name;
        this.cardDeck = cardDeck;
    }

    public void pick(Card card) {
        cardDeck.addNewCard(card);
    }

    public boolean isBust() {
        return getTotalScore() > BUST_LIMIT;
    }

    public int getTotalScore() {
        return cardDeck.calculateScore(BUST_LIMIT);
    }

    public boolean isMoreCardAble() {
        return getTotalScore() < BUST_LIMIT;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public Name getName() {
        return name;
    }

}

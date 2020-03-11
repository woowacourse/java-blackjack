package model;

public abstract class User {
    protected final String name;
    protected final CardHand cardHand;

    public User(CardHand cardHand){
        this("딜러", cardHand);
    }

    public User(String name, CardHand cardHand){
        this.name = name;
        this.cardHand = cardHand;
    }

    public String getName() {
        return name;
    }

    public void drawCard(Card card) {
        cardHand.addCard(card);
    }

    public CardHand getCardHand() {
        return cardHand;
    }
}

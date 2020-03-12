package model;

public abstract class User {
    protected final String name;
    protected final CardHand cardHand;


    public User(CardHand cardHand) {
        this("딜러", cardHand);
    }

    public User(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public abstract String toStringCardHand();

    public void drawCard(CardHand cardHand) {
        for(Card drawCard: cardHand.getCards()){
            this.cardHand.addCard(drawCard);
        }
    }

    public boolean isBust(){
        return getScore()>21;
    }

    public CardHand getCardHand() {
        return cardHand;
    }

    public int getScore() {
        return cardHand.calculateScore();
    }

    @Override
    public String toString() {
        return name;
    }
}

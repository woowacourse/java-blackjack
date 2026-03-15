package blackjack.domain;

public abstract class Participant {

    private final Name name;
    private final Hand hand;

    protected Participant(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public abstract void recieveCard(Card card);

    public int getCardCount() {
        return hand.getCount();
    }

    public String getCardNames() {
        return hand.getCardNames();
    }


    public int getTotalPoint() {
        return hand.getTotalPoint();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public String getName() {
        return name.getValue();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }


    public String getFirstCardName() {
        return hand.getFirstCardName();
    }

    public abstract boolean shouldDraw();

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
}

package blackjack.domain;

public abstract class Participant {

    private final String name;
    private final Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
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
        return name;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }


    public String getFirstCardName() {
        return hand.getFirstCardName();
    }

}

package domain;

public class Dealer extends Participant {

    public Card getOpenCard() {
        return hand.getHand().getFirst();
    }

    public boolean shouldHit() {
        return hand.calculateSum() <= 16;
    }
}

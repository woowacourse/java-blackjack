package blackjack.model.card;

public abstract class Finished implements HandState{

    public void stay(Hand hand) {
        hand.setState(this);
    }
}

package domain;

public class Dealer extends Player {

    public Dealer(String name) {
        super(name);
    }

    public Hand getOnlyFirstHand() {
        Hand newHand = new Hand();
        newHand.addCard(hand.getFirstCard());
        return newHand;
    }
}

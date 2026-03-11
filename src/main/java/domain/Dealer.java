package domain;

public class Dealer extends Participant {

    public Boolean determineDealerDealMore() {
        return hand.determineDealerDealMore();
    }

    public Card getFirstCard() {
        return hand.getCards().getFirst();
    }
}

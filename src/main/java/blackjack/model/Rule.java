package blackjack.model;

public class Rule {

    public boolean shouldDealerDraw(final Dealer dealer) {
        return dealer.getCards().sumAll() < 17;
    }

}

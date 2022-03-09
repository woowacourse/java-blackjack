package blackjack.domain;

public class Dealer extends Gamer {
    private static final int DEALER_STAND_CONDITION = 16;

    @Override
    public void addCard(Card card) {
        if (getCardGroupSum() > DEALER_STAND_CONDITION) {
            return;
        }

        super.addCard(card);
    }
}

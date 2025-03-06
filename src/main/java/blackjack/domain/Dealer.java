package blackjack.domain;

public class Dealer extends Participant {

    private static final int DEALER_DISTRIBUTE_CARD_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Card openFirstCard() {
        return super.cards.getFirst();
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() <= DEALER_DISTRIBUTE_CARD_THRESHOLD;
    }
}

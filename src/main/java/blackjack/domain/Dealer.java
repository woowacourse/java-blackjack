package blackjack.domain;

public class Dealer extends Participant{

    private static final int CARD_RECEIVE_CRITERIA  = 16;
    private static final int MAX_CARD_COUNT = 4;

    public Dealer() {
        super();
    }

    @Override
    public boolean canReceive() {
        return isUnderScore() && isUnderCount();
    }

    private boolean isUnderCount() {
        return cards.getCount() < MAX_CARD_COUNT;
    }

    private boolean isUnderScore() {
        return calculateScore() <= CARD_RECEIVE_CRITERIA;
    }
}

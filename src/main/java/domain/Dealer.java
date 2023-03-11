package domain;

public class Dealer extends Participant {

    private static final int MINIMUM_SUM_OF_DEALERS_CARD = 17;
    private static final int MAXIMUM_SUM_OF_CARD = 21;

    public Dealer(Cards cards) {
        super(cards);
    }

    public boolean isSumUnderStandard() {
        return sumOfParticipantCards() < MINIMUM_SUM_OF_DEALERS_CARD;
    }
}

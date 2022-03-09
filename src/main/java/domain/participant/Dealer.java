package domain.participant;

public class Dealer extends Participant {

    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateSum() <= MAX_CARD_SUM;
    }
}

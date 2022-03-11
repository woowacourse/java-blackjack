package blackjack.domain.participant;

public class Dealer extends Participant {

    public static final String NAME_OF_DEALER = "딜러";
    public static final int BOUND_FOR_ADDITIONAL_CARD = 16;

    public Dealer() {
        super(NAME_OF_DEALER);
    }

    public boolean needMoreCard() {
        return getScore() <= BOUND_FOR_ADDITIONAL_CARD;
    }
}

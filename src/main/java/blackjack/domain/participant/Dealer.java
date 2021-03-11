package blackjack.domain.participant;

public class Dealer extends Participant{
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public String getFirstCardsInfoToString() {
        return state.cards().getFirstCardInfoToString();
    }
}

package blackjack.model.participant;

import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_PICK_THRESHOLD = 16;

    private Dealer(Hands hands) {
        super(DEALER_NAME, hands);
    }

    public static Dealer create() {
        return new Dealer(Hands.empty());
    }

    @Override
    public List<CardDto> getInitCards() {
        return List.of(hands.getFirstCard());
    }

    public boolean canPick() {
        return !hands.hasScoreHigherThan(DEALER_PICK_THRESHOLD);
    }
}

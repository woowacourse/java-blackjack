package domain.participant;

import static config.BlackjackGameConstant.*;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_ADDITIONAL_DRAW_CONDITION = 16;

    private Dealer() {
        super(ParticipantName.from(DEALER_DISPLAY_NAME));
    }

    public static Dealer create() {
        return new Dealer();
    }

    public boolean canHit() {
        return hand.getBasicScore() <= DEALER_ADDITIONAL_DRAW_CONDITION;
    }

}

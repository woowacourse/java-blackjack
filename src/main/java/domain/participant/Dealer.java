package domain.participant;

import static config.BlackjackGameConstant.*;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    private Dealer() {
        super(ParticipantName.from(DEALER_DISPLAY_NAME));
    }

    public static Dealer from() {
        return new Dealer();
    }

    public boolean hitIfRequired(CardDeck cardDeck) {
        if (canHit()) {
            List<Card> cards = cardDeck.draw(DEFAULT_CARD_DRAW_COUNT);
            hand.addUp(Hand.from(cards));
            return true;
        }
        return false;
    }

    public boolean canHit() {
        return hand.getBasicScore() <= DEALER_ADDITIONAL_DRAW_CONDITION;
    }

}

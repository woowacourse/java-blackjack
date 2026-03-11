package domain.participant;

import static config.BlackjackGameConstant.*;

import domain.card.CardDeck;

public class Dealer extends Participant {

    private Dealer() {
        super(ParticipantName.from(DEALER_DISPLAY_NAME));
    }

    public static Dealer from() {
        return new Dealer();
    }

    public boolean hitIfRequired(CardDeck cardDeck) {
        if (canHit()) {
            cardDeck.draw(hand, DEFAULT_CARD_DRAW_COUNT);
            return true;
        }
        return false;
    }

    public boolean canHit() {
        return hand.getBasicScore() <= DEALER_ADDITIONAL_DRAW_CONDITION;
    }

}

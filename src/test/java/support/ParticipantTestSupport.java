package support;

import domain.card.Card;
import domain.card.Hand;
import domain.participant.Participant;
import java.lang.reflect.Field;

public final class ParticipantTestSupport {
    private static final String HAND_FIELD_NAME = "hand";

    private ParticipantTestSupport() {
    }

    public static void addCards(final Participant participant, final Card... cards) {
        final Hand hand = extractHand(participant);
        for (Card card : cards) {
            hand.addCard(card);
        }
    }

    private static Hand extractHand(final Participant participant) {
        try {
            final Field handField = Participant.class.getDeclaredField(HAND_FIELD_NAME);
            handField.setAccessible(true);
            return (Hand) handField.get(participant);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new IllegalStateException("Participant hand field could not be accessed in test.", exception);
        }
    }
}

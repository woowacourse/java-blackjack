package blackjack.testutil;

import blackjack.domain.Deck;
import blackjack.domain.participant.Participant;

public class CardDrawer {
    public static void addAllCards(Deck deck, Participant participant) {
        runUntilException(() -> participant.addCard(deck));
    }

    private static void runUntilException(Runnable operation) {
        boolean isExceptionOccurred = false;
        while (!isExceptionOccurred) {
            isExceptionOccurred = isExceptionOccurred(operation);
        }
    }

    private static boolean isExceptionOccurred(Runnable operation) {
        try {
            operation.run();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}

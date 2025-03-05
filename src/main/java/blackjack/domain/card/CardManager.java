package blackjack.domain.card;

import blackjack.domain.random.CardGenerator;
import blackjack.domain.random.CardRandomGenerator;
import java.util.HashMap;
import java.util.Map;

public class CardManager {

    private final Map<Card, Boolean> cardsUsageStatus;
    private final CardGenerator cardGenerator;

    public CardManager(final CardGenerator cardGenerator) {
        this.cardsUsageStatus = initializeCardsUsageStatus();
        this.cardGenerator = cardGenerator;
    }

    private static Map<Card, Boolean> initializeCardsUsageStatus() {
        final Map<Card, Boolean> cardsUsageStatus = new HashMap<>();
        for (Card card : CardRandomGenerator.CARDS) {
            cardsUsageStatus.put(card, false);
        }
        return cardsUsageStatus;
    }

    public Card spreadOneCard() {
        final Card card = cardGenerator.pickRandomCard();
        if (cardsUsageStatus.get(card)) {
            return spreadOneCard();
        }
        cardsUsageStatus.put(card, true);
        return card;
    }
}

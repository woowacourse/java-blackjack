package blackjack.domain.card;

import blackjack.domain.random.CardGenerator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class CardManager {

    private final Map<Card, Boolean> cardsUsageStatus;
    private final CardGenerator cardGenerator;

    public CardManager(final CardGenerator cardGenerator) {
        this.cardsUsageStatus = initializeCardsUsageStatus();
        this.cardGenerator = cardGenerator;
    }

    private static Map<Card, Boolean> initializeCardsUsageStatus() {
        final Map<Card, Boolean> cardsUsageStatus = new HashMap<>();
        for (Card card : CardGenerator.DECKS) {
            cardsUsageStatus.put(card, false);
        }
        return cardsUsageStatus;
    }

    public Cards spreadCards(final int count) {
        return new Cards(IntStream.range(0, count)
                .mapToObj(o -> spreadOneCard())
                .toList());
    }

    private Card spreadOneCard() {
        final Card card = cardGenerator.pickCard();
        if (cardsUsageStatus.get(card)) {
            return spreadOneCard();
        }
        cardsUsageStatus.put(card, true);
        return card;
    }
}

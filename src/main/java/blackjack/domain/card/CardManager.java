package blackjack.domain.card;

import blackjack.domain.random.CardGenerator;
import blackjack.domain.random.CardRandomGenerator;
import java.util.HashMap;
import java.util.List;
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
        for (Card card : CardRandomGenerator.CARDS) {
            cardsUsageStatus.put(card, false);
        }
        return cardsUsageStatus;
    }

    public List<Card> spreadCards(final int count) {
        return IntStream.range(0, count)
                .mapToObj(o -> spreadOneCard())
                .toList();
    }

    private Card spreadOneCard() {
        final Card card = cardGenerator.pickRandomCard();
        if (cardsUsageStatus.get(card)) {
            return spreadOneCard();
        }
        cardsUsageStatus.put(card, true);
        return card;
    }
}

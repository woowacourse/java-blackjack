package blackjack.model.cardgenerator;

import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.vo.Card;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCardGenerator implements CardGenerator {
    private static final List<Card> cardCache = initializeCardCache();

    private static List<Card> initializeCardCache() {
        return Arrays.stream(Suit.values())
                .flatMap(suit -> createCardsBySuit(suit).stream())
                .toList();
    }

    private static List<Card> createCardsBySuit(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(suit, denomination))
                .toList();
    }

    @Override
    public Card pick() {
        int cacheSize = cardCache.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(cacheSize);
        return cardCache.get(randomIndex);
    }
}

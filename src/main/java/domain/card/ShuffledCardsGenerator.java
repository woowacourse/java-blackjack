package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ShuffledCardsGenerator {
    public static final int DECK_COUNT = 6;
    private static final List<Card> cachedCards;

    static {
        cachedCards = Stream.of(Symbol.values())
                .flatMap(symbol -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(symbol, rank)))
                .toList();
    }

    public List<Card> generate() {
        List<Card> cards = generateBlackJackCards();
        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> generateBlackJackCards() {
        List<Card> blackJackCards = new ArrayList<>();
        for (int count = 0; count < DECK_COUNT; count++) {
            blackJackCards.addAll(new ArrayList<>(cachedCards));
        }
        return blackJackCards;
    }
}

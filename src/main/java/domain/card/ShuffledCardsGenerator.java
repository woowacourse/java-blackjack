package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ShuffledCardsGenerator {
    private static final int DUPLICATES_COUNT = 6;

    public List<Card> generate() {
        List<Card> cards = generateBlackJackCards();
        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> generateBlackJackCards() {
        List<Card> blackJackCards = new ArrayList<>();
        List<Card> cards = generateCardsOfOnePack();

        for (int count = 0; count < DUPLICATES_COUNT; count++) {
            blackJackCards.addAll(cards);
        }
        return blackJackCards;
    }

    private List<Card> generateCardsOfOnePack() {
        return Stream.of(Symbol.values())
                .flatMap(symbol -> Rank.getRanks().stream()
                        .map(rank -> new Card(symbol, rank)))
                .toList();
    }
}

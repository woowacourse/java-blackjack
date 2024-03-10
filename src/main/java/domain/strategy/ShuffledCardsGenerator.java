package domain.strategy;

import domain.Deck;
import domain.CardsGenerator;
import domain.card.Card;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShuffledCardsGenerator implements CardsGenerator {
    private static final int DUPLICATES_COUNT = 6;

    @Override
    public List<Card> generate() {
        List<Card> cards = Stream.generate(Deck::new)
                .limit(DUPLICATES_COUNT)
                .flatMap(deck -> deck.getCards().stream())
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return cards;
    }
}

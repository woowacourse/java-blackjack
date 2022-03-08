package blackjack.domain.strategy;

import blackjack.domain.Card;
import blackjack.domain.CardPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCardStrategy implements CardStrategy {

    private final List<Card> cards;

    public RandomCardStrategy() {
        final List<Card> cards = new ArrayList<>();
        for (CardPattern cardPattern : CardPattern.values()) {
            cards.addAll(createCardsPerPattern(cardPattern));
        }
        this.cards = cards;
    }

    private List<Card> createCardsPerPattern(CardPattern cardPattern) {
        return IntStream.rangeClosed(1, 13)
                .mapToObj(number -> new Card(cardPattern, number))
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> generate() {
        Collections.shuffle(cards);

        return new ArrayList<>(cards);
    }
}

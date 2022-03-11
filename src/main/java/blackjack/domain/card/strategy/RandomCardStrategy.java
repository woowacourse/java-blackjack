package blackjack.domain.card.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;

public class RandomCardStrategy implements CardStrategy {

    private final List<Card> cards;

    public RandomCardStrategy() {
        final List<Card> cards = new ArrayList<>();
        for (final CardPattern cardPattern : CardPattern.values()) {
            cards.addAll(createCardsPerPattern(cardPattern));
        }
        this.cards = cards;
    }

    private List<Card> createCardsPerPattern(final CardPattern cardPattern) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(cardPattern, cardNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> generate() {
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }

}

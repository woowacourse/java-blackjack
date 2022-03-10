package blackjack.domain.card.strategy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;

import java.util.*;
import java.util.stream.Collectors;

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

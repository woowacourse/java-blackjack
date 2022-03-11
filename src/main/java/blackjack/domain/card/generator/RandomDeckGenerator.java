package blackjack.domain.card.generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;

public class RandomDeckGenerator implements DeckGenerator {

    private final List<Card> cards;

    public RandomDeckGenerator() {
        this.cards = Arrays.stream(CardPattern.values())
                .map(this::createCardsPerPattern)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Card> createCardsPerPattern(final CardPattern cardPattern) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(cardNumber, cardPattern))
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> generate() {
        final List<Card> shuffledCards = new LinkedList<>(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }

}

package blackjack.domain.card.generator;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toUnmodifiableSet;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RandomCardsGenerator implements CardsGenerator {

    private static final Set<Card> VALUES = createCards();

    private static Set<Card> createCards() {
        return Arrays.stream(Denomination.values())
                .map(RandomCardsGenerator::createIntactCards)
                .flatMap(Set::stream)
                .collect(toUnmodifiableSet());
    }

    private static Set<Card> createIntactCards(Denomination denomination) {
        return Arrays.stream(Suit.values())
                .map(suit -> new Card(denomination, suit))
                .collect(toSet());
    }

    @Override
    public List<Card> generate() {
        LinkedList<Card> cards = new LinkedList<>(VALUES);
        Collections.shuffle(cards);

        return cards;
    }
}

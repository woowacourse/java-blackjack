package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomDeckGenerator implements DeckGenerator {

    private static final List<Card> baseDeck;

    static {
        baseDeck = Arrays.stream(CardShape.values())
                .map(RandomDeckGenerator::generateCardsFrom)
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Card> generateCardsFrom(final CardShape shape) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(shape, cardNumber))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Card> generate() {
        final List<Card> generatedCards = new ArrayList<>(baseDeck);
        Collections.shuffle(generatedCards);
        return generatedCards;
    }
}

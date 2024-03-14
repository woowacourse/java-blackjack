package blackjack.domain.deck;

import blackjack.domain.card.Rank;
import blackjack.domain.card.Pattern;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeckGenerator {

    private static final List<Card> deck = createDeck();

    private DeckGenerator() {
    }

    public static List<Card> generateDeck() {
        return new ArrayList<>(deck);
    }

    private static List<Card> createDeck() {
        return Arrays.stream(Pattern.values())
                .map(DeckGenerator::createCard)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Card> createCard(Pattern pattern) {
        return Arrays.stream(Rank.values())
                .map(cardNumber -> new Card(pattern, cardNumber))
                .collect(Collectors.toList());
    }
}

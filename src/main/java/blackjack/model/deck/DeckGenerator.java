package blackjack.model.deck;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.card.CardProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckGenerator {

    private static final List<Card> deck;

    static {
        deck = createDeck();
    }

    public static List<Card> generateDeck() {
        return new ArrayList<>(deck);
    }

    private static List<Card> createDeck() {
        return Arrays.stream(CardPattern.values())
                .flatMap(DeckGenerator::createCard)
                .collect(Collectors.toList());
    }

    private static Stream<Card> createCard(CardPattern cardPattern) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(new CardProperties(cardPattern, cardNumber)));
    }
}

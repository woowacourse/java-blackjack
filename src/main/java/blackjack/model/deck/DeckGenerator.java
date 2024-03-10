package blackjack.model.deck;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.card.CardProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeckGenerator {

    private static final List<Card> deck = createDeck();

    public static List<Card> generateDeck() {
        return new ArrayList<>(deck);
    }

    private static List<Card> createDeck() {
        return Arrays.stream(CardPattern.values())
                .flatMap(cardPattern -> createCard(cardPattern).stream())
                .collect(Collectors.toList());
    }

    private static List<Card> createCard(CardPattern cardPattern) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(new CardProperties(cardPattern, cardNumber)))
                .collect(Collectors.toList());
    }
}

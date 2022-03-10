package blackjack.testutil;

import blackjack.domain.CardDeck;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardFixtureGenerator {

    public static List<Card> createCards(Card firstCard, Card... remainCard) {
        final List<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.addAll(Arrays.stream(remainCard)
                .collect(Collectors.toList()));
        return cards;
    }

    public static void pollCards(final CardDeck cardDeck, final int pollCount) {
        for (int i = 0; i < pollCount; i++) {
            cardDeck.provideCard();
        }
    }
}

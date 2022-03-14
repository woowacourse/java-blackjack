package blackjack.testutil;

import blackjack.domain.CardDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardFixtureGenerator {

    public static Cards createCards(Card firstCard, Card... remainCard) {
        final List<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.addAll(Arrays.stream(remainCard)
                .collect(Collectors.toList()));
        return new Cards(cards);
    }

    public static void pollCards(final CardDeck cardDeck, final int pollCount) {
        for (int i = 0; i < pollCount; i++) {
            cardDeck.provideCard();
        }
    }
}

package blackjack.testutil;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import java.util.List;

public class CardFixtureGenerator {

    public static Cards createCards(Card firstCard, Card secondCard) {
        return new Cards(List.of(firstCard, secondCard));
    }

    public static void pollCards(final CardDeck cardDeck, final int pollCount) {
        for (int i = 0; i < pollCount; i++) {
            cardDeck.provideCard();
        }
    }
}

package blackjack.domain.testutil;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.PlayingCard;
import java.util.ArrayDeque;
import java.util.Deque;

public class CardDeckFixtureGenerator {
    public static CardDeck createCardDeck(final PlayingCard card1,
                                    final PlayingCard... cards) {
        Deque<PlayingCard> rawCardDeck = new ArrayDeque<>();
        rawCardDeck.push(card1);
        for (PlayingCard card : cards) {
            rawCardDeck.push(card);
        }
        return new CardDeck(() -> rawCardDeck);
    }
}

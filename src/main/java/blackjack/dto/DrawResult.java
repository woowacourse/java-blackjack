package blackjack.dto;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.List;

public record DrawResult(
    Hand drewCard,
    Deck drewDeck
) {

    public static DrawResult of(List<Card> drewCard, List<Card> drewDeck) {
        return new DrawResult(Hand.from(drewCard), Deck.from(drewDeck));
    }
}

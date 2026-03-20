package blackjack.dto;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.List;

public record DrawOutcome(
    Hand drewCard,
    Deck drewDeck
) {

    public static DrawOutcome of(List<Card> drewCard, List<Card> drewDeck) {
        return new DrawOutcome(Hand.from(drewCard), Deck.from(drewDeck));
    }
}

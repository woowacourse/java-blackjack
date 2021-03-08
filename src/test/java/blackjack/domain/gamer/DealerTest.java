package blackjack.domain.gamer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hands;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("기준에 따라 카드 뽑기 성공")
    @Test
    void checkBoundary() {
        List<Card> lowerCards = new ArrayList<>();
        lowerCards.add(Card.of(Suit.HEART, Denomination.EIGHT));
        lowerCards.add(Card.of(Suit.CLUB, Denomination.EIGHT));
        Hands lowerHands = new Hands(lowerCards);

        List<Card> higherCards = new ArrayList<>();
        higherCards.add(Card.of(Suit.HEART, Denomination.EIGHT));
        higherCards.add(Card.of(Suit.CLUB, Denomination.NINE));
        Hands higherHands = new Hands(higherCards);

        final Dealer higherDealer = new Dealer(higherHands);
        final Dealer lowerDealer = new Dealer(lowerHands);

        assertAll(
                () -> assertTrue(lowerDealer.checkBoundary()),
                () -> assertFalse(higherDealer.checkBoundary())
        );

    }
}
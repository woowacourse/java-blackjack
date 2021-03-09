package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    @DisplayName("기준에 따라 카드 뽑기 성공")
    @Test
    void checkBoundary() {
        List<Card> lowerCards = new ArrayList<>();
        lowerCards.add(Card.of(Suit.HEART, Denomination.EIGHT));
        lowerCards.add(Card.of(Suit.CLUB, Denomination.EIGHT));

        List<Card> higherCards = new ArrayList<>();
        higherCards.add(Card.of(Suit.HEART, Denomination.EIGHT));
        higherCards.add(Card.of(Suit.CLUB, Denomination.NINE));

        final Dealer higherDealer = new Dealer();
        final Dealer lowerDealer = new Dealer();

        higherDealer.initState(higherCards);
        lowerDealer.initState(lowerCards);

        assertAll(
                () -> assertTrue(lowerDealer.canDraw()),
                () -> assertFalse(higherDealer.canDraw())
        );

    }
}
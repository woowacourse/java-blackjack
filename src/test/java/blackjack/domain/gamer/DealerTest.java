package blackjack.domain.gamer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
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
        Hands lowerHands = new Hands(lowerCards);
        lowerHands.addCard(Card.create(CardSymbol.HEART, CardValue.EIGHT));
        lowerHands.addCard(Card.create(CardSymbol.CLUB, CardValue.EIGHT));

        List<Card> higherCards = new ArrayList<>();
        Hands higherHands = new Hands(higherCards);
        higherHands.addCard(Card.create(CardSymbol.HEART, CardValue.EIGHT));
        higherHands.addCard(Card.create(CardSymbol.CLUB, CardValue.NINE));

        final Dealer higherDealer = new Dealer(higherHands);
        final Dealer lowerDealer = new Dealer(lowerHands);

        assertAll(
                () -> assertTrue(lowerDealer.checkBoundary()),
                () -> assertFalse(higherDealer.checkBoundary())
        );

    }
}
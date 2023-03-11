package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러 16점에서의 카드를 더 받을 수 있는지 테스트")
    public void testCanReceiveCard() {
        //given
        List<Card> cardsScore16 = List.of(
            new Card(Suit.SPADE, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.SIX));
        Dealer dealer = new Dealer();

        //when
        cardsScore16.forEach(dealer::addCard);

        //then
        assertTrue(dealer.canReceiveCard());
    }

    @Test
    @DisplayName("딜러 17점에서의 카드를 더 받을 수 없는지 테스트")
    public void testCanNotReceiveCard() {
        //given
        List<Card> cardsScore17 = List.of(
            new Card(Suit.SPADE, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.SEVEN));
        Dealer dealer = new Dealer();

        //when
        cardsScore17.forEach(dealer::addCard);

        //then
        assertFalse(dealer.canReceiveCard());
    }
}

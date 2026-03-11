package domain.participant;

import domain.card.Card;
import domain.card.CardSuit;
import domain.card.CardValue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러는 점수의 합이 16 이하이면 카드를 한 장 받는다.")
    void receiveCardTest() {
        List<Card> hand = new ArrayList<>(List.of(
                new Card(CardValue.THREE, CardSuit.CLUB),
                new Card(CardValue.FOUR, CardSuit.CLUB)));
        Dealer dealer = new Dealer(hand);

        Assertions.assertTrue(dealer.canReceiveCard());
    }

    @Test
    @DisplayName("딜러는 점수의 합이 17 이상이면 카드를 받지 않는다.")
    void notReceiveCardTest() {
        List<Card> hand = new ArrayList<>(List.of(
                new Card(CardValue.TEN, CardSuit.CLUB),
                new Card(CardValue.SEVEN, CardSuit.CLUB)));
        Dealer dealer = new Dealer(hand);

        Assertions.assertFalse(dealer.canReceiveCard());
    }
}

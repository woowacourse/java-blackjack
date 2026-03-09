package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러는 점수의 합이 16 이하이면, 카드를 한 장 받는다.")
    void receiveCardTest() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck);

        boolean isReceiveCard = dealer.isReceiveCard();

        Assertions.assertTrue(isReceiveCard);
    }
}

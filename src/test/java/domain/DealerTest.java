package domain;

import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러는 점수의 합이 16 이하이면, 카드를 한 장 받는다.")
    void receiveCardTest() {
        List<Card> cards = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.FOUR, CardShape.CLUB));
        Dealer dealer = new Dealer(cards);

        boolean isReceiveCard = dealer.isReceiveCard();

        Assertions.assertTrue(isReceiveCard);
    }
}

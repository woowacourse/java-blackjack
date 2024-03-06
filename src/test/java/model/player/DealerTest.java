package model.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("카드의 합이 16이하일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertTrue(dealer.receiveCard());
    }

    @DisplayName("카드의 합이 16초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.CLOVER, CardNumber.NINE));
        assertFalse(dealer.receiveCard());
    }

    @DisplayName("딜러면 참을 반환한다.")
    @Test
    void isDealer() {
        Dealer dealer = new Dealer();
        assertTrue(dealer.isDealer());
    }
}

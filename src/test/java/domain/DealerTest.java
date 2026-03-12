package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 점수가 16점 이하일 때 카드를 더 받을 수 있다.")
    void isContinueGame_UnderScore16_ReturnTrue() {

        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receiveCard(new Card(Shape.SPADE, Number.TEN));
        dealer.receiveCard(new Card(Shape.HEART, Number.SIX));

        boolean result = dealer.isContinueGame();
        assertEquals(true, result);
    }

    @Test
    @DisplayName("딜러는 점수가 17점 이상일 때 카드를 더 받을 수 없다.")
    void isContinueGame_OverScore17_ReturnFalse() {

        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receiveCard(new Card(Shape.SPADE, Number.TEN));
        dealer.receiveCard(new Card(Shape.HEART, Number.SEVEN));

        boolean result = dealer.isContinueGame();
        assertEquals(false, result);
    }
}

package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 점수가_16점_이하이면_카드를_추가로_받을_수_있다() {
        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receiveCard(new Card(Shape.SPADE, Number.TEN));
        dealer.receiveCard(new Card(Shape.HEART, Number.SIX));

        boolean result = dealer.isContinueGame();
        assertEquals(true, result);
    }

    @Test
    void 점수가_17점_이상이면_카드를_추가로_받을_수_없다() {
        Dealer dealer = new Dealer(new Name("딜러"));
        dealer.receiveCard(new Card(Shape.SPADE, Number.TEN));
        dealer.receiveCard(new Card(Shape.HEART, Number.SEVEN));

        boolean result = dealer.isContinueGame();
        assertEquals(false, result);
    }
}

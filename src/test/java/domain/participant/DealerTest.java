package domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Help;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 점수가_16점_이하이면_카드를_추가로_받을_수_있다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Help(Shape.SPADE, domain.card.Number.TEN));
        dealer.receiveCard(new Help(Shape.HEART, domain.card.Number.SIX));

        boolean result = dealer.isContinueGame();
        assertEquals(true, result);
    }

    @Test
    void 점수가_17점_이상이면_카드를_추가로_받을_수_없다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Help(Shape.SPADE, domain.card.Number.TEN));
        dealer.receiveCard(new Help(Shape.HEART, Number.SEVEN));

        boolean result = dealer.isContinueGame();
        assertEquals(false, result);
    }
}

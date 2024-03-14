package model.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.TWO)));
    }

    @DisplayName("카드의 합이 16이하일 때는 참을 반환한다.")
    @Test
    void noticeTrue() {
        assertTrue(dealer.isHit());
    }

    @DisplayName("카드의 합이 16초과일 때는 거짓을 반환한다.")
    @Test
    void noticeFalse() {
        dealer.addCards(List.of(new Card(CardShape.CLOVER, CardNumber.NINE)));
        assertFalse(dealer.isHit());
    }
}

package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러 생성 확인")
    void create() {
        assertEquals(dealer.getName(), "딜러");
    }

    @Test
    @DisplayName("딜러가 받은 카드 계산")
    void dealerDealCard() {
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.ACE));
        assertEquals(dealer.getPoint(), 11);
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 있는지 확인")
    void dealerPossibleReceiveCard() {
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.KING));
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.SIX));
        assertTrue(dealer.canReceiveCard());
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 없는지 확인")
    void dealerImpossibleReceiveCard() {
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.KING));
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.SEVEN));
        assertFalse(dealer.canReceiveCard());
    }
}

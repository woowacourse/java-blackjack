package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.KING));
    }

    @Test
    @DisplayName("딜러 생성 확인")
    void create() {
        assertEquals("딜러", dealer.getName());
    }

    @Test
    @DisplayName("딜러가 받은 카드 계산")
    void dealerDealCard() {
        assertEquals(10, dealer.makeJudgingPoint());
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 있는지 확인")
    void dealerPossibleReceiveCard() {
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SIX));
        assertTrue(dealer.canReceiveCard());
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 없는지 확인")
    void dealerImpossibleReceiveCard() {
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SEVEN));
        assertFalse(dealer.canReceiveCard());
    }

    @Test
    @DisplayName("딜러의 값 비교 확인")
    void dealerIsSmallerThan() {
        assertTrue(dealer.isSmallerThan(11));
        assertFalse(dealer.isSmallerThan(10));
        assertTrue(dealer.isSameThan(10));
        assertFalse(dealer.isSameThan(11));
        assertTrue(dealer.isBiggerThan(9));
        assertFalse(dealer.isBiggerThan(10));
    }
}

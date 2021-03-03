package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        assertEquals(dealer.calculatePoint(), 1);
    }
}

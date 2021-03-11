package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealerTest {
    @DisplayName("isStay - 딜러는 16이하일 때 스테이 할 수 없다.")
    @Test
    void check16() {
        Dealer dealer = new Dealer();

        dealer.hit(Fixture.CLUBS_TEN, Fixture.CLUBS_TWO);

        assertTrue(dealer.isDealerDrawScore());
    }

    @DisplayName("isStay - 딜러는 17 이상일 때 스테이한다.")
    @Test
    void check17() {
        Dealer dealer = new Dealer();

        dealer.hit(Fixture.CLUBS_KING, Fixture.CLUBS_TEN);

        assertFalse(dealer.isDealerDrawScore());
    }
}

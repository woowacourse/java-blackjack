package blackjack.domain.state;

import blackjack.domain.Dealer;
import blackjack.domain.Fixture;
import blackjack.state.DealerHit;
import blackjack.state.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealerHitTest {

    @DisplayName("딜러 최초 블랙잭 상태 확인")
    @Test
    void dealerInitialHitBlackJack() {
        Dealer dealer = new Dealer();

        dealer.initialHit(Fixture.CLUBS_KING, Fixture.CLUBS_ACE);

        assertTrue(dealer.isBlackJack());
    }

    @DisplayName("딜러 최초 Hit 상태 확인")
    @Test
    void dealerInitialHit() {
        Dealer dealer = new Dealer();

        dealer.initialHit(Fixture.CLUBS_TEN, Fixture.CLUBS_TWO);

        assertTrue(dealer.getState() instanceof DealerHit);
    }

    @DisplayName("딜러 스테이 상태 확인")
    @Test
    void dealerInitialStay() {
        Dealer dealer = new Dealer();

        dealer.initialHit(Fixture.CLUBS_TEN, Fixture.CLUBS_KING);

        assertTrue(dealer.getState() instanceof Stay);
    }

}

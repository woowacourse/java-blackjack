package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BetTest {

    @Test
    @DisplayName("수익은 0원일 수 있다")
    void takeRevenueFromTestWhenRevenueZero() {
        Bet bet = new Bet(1000);

        assertThat(bet.getRevenue()).isEqualTo(0);
    }

    @Test
    @DisplayName("수익은 1배씩 발생한다")
    void takeRevenueFromTestWhenNormalCase() {
        Bet bet = new Bet(1000);

        bet.addRevenue();

        assertThat(bet.getRevenue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("수익은 1.5배씩 발생한다")
    void takeRevenueFromTestAboutDecrease() {
        Bet bet = new Bet(1000);

        bet.addBonusRevenue();

        assertThat(bet.getRevenue()).isEqualTo(1500);
    }

    @Test
    @DisplayName("다른 Bet의 수익만큼을 감소시킬 수 있다")
    void takeRevenueFromTestFromManyBet() {
        Bet bet = new Bet(1000);
        Bet otherBet = new Bet(3000);

        otherBet.addRevenue();
        bet.payFor(otherBet);

        assertThat(bet.getRevenue()).isEqualTo(-3000);
    }
}


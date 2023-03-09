package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BetTest {

    @Test
    @DisplayName("배팅 금액이 0원이면 예외가 발생한다")
    void betAmountCreateFailWhenBetAmountZero() {
        assertThatThrownBy(() -> new Bet(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("배팅 금액이 0원 미만이면 예외가 발생한다")
    void betAmountCreateFailWhenBetAmountUnderZero() {
        assertThatThrownBy(() -> new Bet(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수익은 0원일 수 있다")
    void takeRevenueFromTestWhenRevenueZero() {
        Bet bet = new Bet(1000);

        assertThat(bet.getRevenue()).isEqualTo(0);
    }

    @Test
    @DisplayName("상대의 원금을 통해 수익을 계산할 수 있다")
    void takeRevenueFromTestWhenNormalCase() {
        Bet bet = new Bet(1000);
        Bet otherBet = new Bet(1000);

        bet.takeRevenueFrom(otherBet);

        assertThat(bet.getRevenue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("수익을 얻는 경우, 상대의 수익은 그만큼 감소한다")
    void takeRevenueFromTestAboutDecrease() {
        Bet bet = new Bet(1000);
        Bet otherBet = new Bet(1000);

        bet.takeRevenueFrom(otherBet);

        assertThat(otherBet.getRevenue()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("여러 곳에서 수익을 얻을 수 있다")
    void takeRevenueFromTestFromManyBet() {
        Bet bet = new Bet(1000);
        Bet otherBet = new Bet(3000);
        Bet anotherBet = new Bet(2000);

        bet.takeRevenueFrom(otherBet);
        bet.takeRevenueFrom(anotherBet);

        assertThat(bet.getRevenue()).isEqualTo(5000);
    }

    @Test
    @DisplayName("수익을 얻고, 다시 잃을 수 있다")
    void takeRevenueFromTestWhenTakeAndGive() {
        Bet bet = new Bet(1000);
        Bet otherBet = new Bet(3000);
        Bet anotherBet = new Bet(2000);

        bet.takeRevenueFrom(otherBet);
        anotherBet.takeRevenueFrom(bet);

        assertThat(bet.getRevenue()).isEqualTo(2000);
    }
}


package domain;

import domain.player.Status;
import domain.stake.Bet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetTest {

    @Test
    @DisplayName("0미만일 수  없다")
    void minimumTest() {
        assertThatThrownBy(() -> Bet.from(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("미만");
    }

    @Test
    @DisplayName("100_000 초과일 수 없다")
    void maximumTest() {
        assertThatThrownBy(() -> Bet.from(100_001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초과");
    }


    @Test
    @DisplayName("이기면 베팅 금액의 1배를 반환한다")
    void calculatePrize() {
        //given
        Bet bet = Bet.from(100);
        //when
        Bet dealerPrize = bet.getPrize(Status.WIN);
        int value = dealerPrize.getValue();
        //then
        assertThat(value).isEqualTo(100);
    }

    @Test
    @DisplayName("지면 베팅 금액의 -1배를 반환한다")
    void calculatePrize2() {
        //given
        Bet bet = Bet.from(100);
        //when
        Bet dealerPrize = bet.getPrize(Status.LOSE);
        int value = dealerPrize.getValue();
        //then
        assertThat(value).isEqualTo(-100);
    }

    @Test
    @DisplayName("비기면 베팅 금액의 0배를 반환한다")
    void calculatePrize3() {
        //given
        Bet bet = Bet.from(100);
        //when
        Bet dealerPrize = bet.getPrize(Status.DRAW);
        int value = dealerPrize.getValue();
        //then
        assertThat(value).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭으로 지면 베팅 금액의 1.5배를 반환한다")
    void calculatePrize4() {
        //given
        Bet bet = Bet.from(100);
        //when
        Bet dealerPrize = bet.getPrize(Status.BLACKJACK_WIN);
        int value = dealerPrize.getValue();
        //then
        assertThat(value).isEqualTo(150);
    }

    @Test
    @DisplayName("Stake 합산 테스트")
    void addStakeTest() {
        //given
        Bet bet1 = Bet.from(150);
        Bet bet2 = Bet.from(100);
        //when
        Bet add = bet1.add(bet2);
        //then
        assertThat(add).isEqualTo(Bet.from(250));
    }

    @Test
    @DisplayName("블랙잭으로 승리해서 얻은 stake는 100_000 초과일 수 있다")
    void stakeUnlimitTest() {
        //given
        Bet bet = Bet.from(100000);
        //when
        Bet playerPrize = bet.getPrize(Status.BLACKJACK_WIN);
        //then
        assertThat(playerPrize.getValue()).isEqualTo(150000);
    }
}

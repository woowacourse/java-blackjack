package domain;

import domain.player.Status;
import domain.stake.Stake;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StakeTest {

    @Test
    @DisplayName("0미만일 수  없다")
    void minimumTest() {
        assertThatThrownBy(() -> Stake.fromBet(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("미만");
    }

    @Test
    @DisplayName("100_000 초과일 수 없다")
    void maximumTest() {
        assertThatThrownBy(() -> Stake.fromBet(100_001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초과");
    }


    @Test
    @DisplayName("이기면 베팅 금액의 1배를 반환한다")
    void calculatePrize() {
        //given
        Stake stake = Stake.fromBet(100);
        //when
        Stake dealerPrize = stake.getPrize(Status.WIN);
        int value = dealerPrize.getValue();
        //then
        assertThat(value).isEqualTo(100);
    }

    @Test
    @DisplayName("지면 베팅 금액의 -1배를 반환한다")
    void calculatePrize2() {
        //given
        Stake stake = Stake.fromBet(100);
        //when
        Stake dealerPrize = stake.getPrize(Status.LOSE);
        int value = dealerPrize.getValue();
        //then
        assertThat(value).isEqualTo(-100);
    }

    @Test
    @DisplayName("비기면 베팅 금액의 0배를 반환한다")
    void calculatePrize3() {
        //given
        Stake stake = Stake.fromBet(100);
        //when
        Stake dealerPrize = stake.getPrize(Status.DRAW);
        int value = dealerPrize.getValue();
        //then
        assertThat(value).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭으로 지면 베팅 금액의 1.5배를 반환한다")
    void calculatePrize4() {
        //given
        Stake stake = Stake.fromBet(100);
        //when
        Stake dealerPrize = stake.getPrize(Status.BLACKJACK_WIN);
        int value = dealerPrize.getValue();
        //then
        assertThat(value).isEqualTo(150);
    }

    @Test
    @DisplayName("Stake 합산 테스트")
    void addStakeTest() {
        //given
        Stake stake1 = Stake.fromBet(150);
        Stake stake2 = Stake.fromBet(100);
        //when
        Stake add = stake1.add(stake2);
        //then
        assertThat(add).isEqualTo(Stake.fromBet(250));
    }

    @Test
    @DisplayName("블랙잭으로 승리해서 얻은 stake는 100_000 초과일 수 있다")
    void stakeUnlimitTest() {
        //given
        Stake stake = Stake.fromBet(100000);
        //when
        Stake playerPrize = stake.getPrize(Status.BLACKJACK_WIN);
        //then
        assertThat(playerPrize.getValue()).isEqualTo(150000);
    }
}

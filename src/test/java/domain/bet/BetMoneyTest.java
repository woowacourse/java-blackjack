package domain.bet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetMoneyTest {

    @Test
    @DisplayName("금액을 받아 BetMoney를 생성할 수 있다.")
    void test1() {
        assertThat(new BetMoney(10000)).isInstanceOf(BetMoney.class);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이면 베팅 금액의 1.5배를 받는다.")
    void test2() {
        BetMoney betMoney = new BetMoney(10000);

        BetMoney applyedBetMoney = betMoney.applyBlackJackBonus();

        assertThat(applyedBetMoney.getAmount()).isEqualTo(15000);
    }

    @Test
    @DisplayName("딜러가 버스트 시 살아남은 플레이어라면 베팅 금액의 2배를 받는다.")
    void test3() {
        BetMoney betMoney = new BetMoney(10000);

        BetMoney applyedBetMoney = betMoney.applyWinBonus();

        assertThat(applyedBetMoney.getAmount()).isEqualTo(20000);
    }

    @Test
    @DisplayName("플레이어가 버스트 시 베팅 금액을 모두 잃는다.")
    void test4() {
        BetMoney betMoney = new BetMoney(10000);

        BetMoney applyedBetMoney = betMoney.applyLossPenalty();

        assertThat(applyedBetMoney.getAmount()).isEqualTo(0);
    }
}
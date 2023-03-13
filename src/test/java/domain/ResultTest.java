package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @DisplayName("플레이어가 승인 경우 베팅 금액만큼 수익을 얻는다.")
    @Test
    void winTest() {
        int bettingMoney = 1000;
        assertThat(Result.WIN.calculateResult(bettingMoney)).isEqualTo(1000);
    }

    @DisplayName("플레이어가 무인 경우 0의 수익을 얻는다.")
    @Test
    void tieTest() {
        int bettingMoney = 1000;
        assertThat(Result.TIE.calculateResult(bettingMoney)).isEqualTo(0);
    }

    @DisplayName("플레이어가 패인 경우 베팅 금액만큼 수익을 잃는다.")
    @Test
    void loseTest() {
        int bettingMoney = 1000;
        assertThat(Result.LOSE.calculateResult(bettingMoney)).isEqualTo(-1000);
    }
}

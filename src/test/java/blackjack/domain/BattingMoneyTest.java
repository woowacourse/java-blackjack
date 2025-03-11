package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattingMoneyTest {

    @DisplayName("금액에 대한 객체가 생성되면, 해당 금액을 갖고 있다.")
    @Test
    void test_BattingMoneyCreate() {
        // given
        BattingMoney battingMoney = new BattingMoney(10000);

        // when
        int amount = battingMoney.intValue();

        // then
        assertThat(amount).isEqualTo(10000);
    }
    
    @DisplayName("플레이어가 블랙잭일때 수익은 배팅한 금액의 1.5배이다")
    @Test
    void test_BlackjackWinningMoney() {
        // given
        BattingMoney battingMoney = new BattingMoney(10000);
        
        // when
        int winMoney = battingMoney.getBlackjackWinningMoney();
    
        // then
        assertThat(winMoney).isEqualTo(15000);
    }

    @DisplayName("수익이 소수면 버리기 처리한다.")
    @Test
    void test_BlackjackWinningMoney_truncation() {
        // given
        BattingMoney battingMoney = new BattingMoney(1);

        // when
        int winMoney = battingMoney.getBlackjackWinningMoney();

        // then
        assertThat(winMoney).isEqualTo(1);
    }

    @DisplayName("이겼을 때 배팅한 금액만큼 그대로 수익이된다.")
    @Test
    void test_winningMoney() {
        // given
        BattingMoney battingMoney = new BattingMoney(10000);

        // when
        int winMoney = battingMoney.getNormalWinningMoney();

        // then
        assertThat(winMoney).isEqualTo(10000);
    }

    @DisplayName("졌을 때 배팅한 금액만큼 수익이 마이너스가 된다.")
    @Test
    void test_losingMoney() {
        // given
        BattingMoney battingMoney = new BattingMoney(10000);

        // when
        int loseMoney = battingMoney.getLosingMoney();

        // then
        assertThat(loseMoney).isEqualTo(10000);
    }

}
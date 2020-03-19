package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingMoneyTest {

    @Test
    @DisplayName("예외처리 테스트")
    void bettingMoneyTest() {
        assertThatThrownBy(() -> new BettingMoney(0))
                .isInstanceOf(IllegalArgumentException.class);

        //프로그램 상 블랙잭으로 승리했을 때 표기가 가능한 가장 큰 값을 초과한 경우
        assertThatThrownBy(() -> new BettingMoney((int) ((int)Integer.MAX_VALUE/1.5) + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

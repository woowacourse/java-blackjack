package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("BettingMoney 인스턴스를 생성한다")
    @Test
    void createBettingMoneyTest() {
        // Given
        int inputMoney = 10000;

        // When
        BettingMoney bettingMoney = new BettingMoney(inputMoney);

        // Then
        assertThat(bettingMoney).isNotNull();
    }

    @DisplayName("10,000원 이하의 금액이 입력되면 예외를 발생 시킨다.")
    @Test
    void validateMoneyTest() {
        // Given
        int inputMoney = 7000;

        // When & Then
        assertThatThrownBy(() -> new BettingMoney(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 최소 10000원부터 가능합니다.");
    }
}

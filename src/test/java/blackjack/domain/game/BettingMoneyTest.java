package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 50000, 100_000, 100_000_000})
    @DisplayName("배팅 금액이 정상적으로 생성된다.")
    void createBetting(int betting) {
        assertDoesNotThrow(() -> new BettingMoney(betting));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1000, 0, 900, 100_000_100, 500_000_000})
    @DisplayName("배팅 금액 허용 범위와 다르면 예외가 발생한다.")
    void createBettingWithWrongRange(int betting) {
        assertThatThrownBy(() -> new BettingMoney(betting))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 최소 1천원, 최대 1억원까지 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 1250, 15550, 55123, 9999_9999})
    @DisplayName("배팅 금액 단위가 백원 단위가 아니면 예외가 발생한다.")
    void createBettingWithWrongMoneyUnit(int betting) {
        assertThatThrownBy(() -> new BettingMoney(betting))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 백원 단위로 입력 가능합니다.");
    }
}

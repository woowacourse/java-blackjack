package blackjack.domain.result;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerBettingMoneyTest {

    @ParameterizedTest(name = "플레이어 금액 범위 검증 테스트")
    @ValueSource(ints = {-1, 0})
    void checkValidMoney(int value) {
        assertThatThrownBy(() -> new PlayerBettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액을 넣어주세요.");
    }
}
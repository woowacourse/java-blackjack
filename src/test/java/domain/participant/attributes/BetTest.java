package domain.participant.attributes;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {
    @DisplayName("베팅 금액이 1000 이하일 경우 예외를 던진다.")
    @ValueSource(ints = {-100, -1, 0, 100, 999})
    @ParameterizedTest
    void validateMinAmount(int amount) {
        assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 최소 1000입니다");
    }

    @DisplayName("베팅 금액이 1000으로 나누어떨어지지 않을 경우 예외를 던진다.")
    @ValueSource(ints = {1100, 10010, 55555})
    @ParameterizedTest
    void validateDivisionByMinAmount(int amount) {
        assertThatThrownBy(() -> new Bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원 단위로 입력해주세요");
    }
}

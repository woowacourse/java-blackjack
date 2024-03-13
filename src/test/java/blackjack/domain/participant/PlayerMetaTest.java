package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerMetaTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "      ", "\n"})
    @DisplayName("공백만을 가진 이름으로 PlayerMeta를 생성하면 예외가 발생한다.")
    void throwsExceptionWhenNameIsBlankTest(String blankName) {
        int validBetAmount = 10000;

        assertThatThrownBy(() -> new PlayerMeta(blankName, validBetAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 비어있습니다.");
    }

    @Test
    @DisplayName("0보다 작은 배팅 금액으로 PlayerMeta를 생성하면 예외가 발생한다.")
    void throwsExceptionWhenBetAmountBelowsZero() {
        String validName = "testPlayer";
        int invalidBetAmount = -1;

        assertThatThrownBy(() -> new PlayerMeta(validName, invalidBetAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("미만일 수 없습니다.");
    }
}
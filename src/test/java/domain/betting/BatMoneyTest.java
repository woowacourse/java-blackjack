package domain.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BatMoneyTest {

    @Nested
    @DisplayName("생성자 예외 테스트")
    class constructor {
        @ParameterizedTest
        @DisplayName("배팅 금액이 양수가 아니면 예외가 발생한다.")
        @CsvSource(value = {"-1", "0"})
        void should_throw_exception_when_money_is_not_positive(int money) {
            // given
            String name = "a";

            // when & then
            assertThatThrownBy(() -> new BatMoney(name, money));
        }

        @ParameterizedTest
        @DisplayName("이름이 빈 문자열이면 예외가 발생한다.")
        @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
        void should_throw_exception_when_name_is_blank(String name) {
            // when & then
            assertThatThrownBy(() -> new BatMoney(name, 10000));
        }
    }
}

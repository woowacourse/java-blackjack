package domain.bet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @Nested
    class FromTest {

        @Nested
        class Success {

            @Test
            void Money_객체_생성을_성공한다() {

                // given
                String input = "100";

                // when
                Money actual = Money.from(input);

                // then
                int expected = 100;
                Assertions.assertThat(actual.value()).isEqualTo(expected);
            }
        }

        @Nested
        class Fail {

            @Test
            void 입력값이_숫자가_아닌_경우_예외를_발생_시켜야_한다() {

                // given
                String input = "noNumber100";

                // when & then
                assertThatThrownBy(() -> {
                    Money.from(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.MONEY_NOT_NUMBER.getMessage());

            }

            @ParameterizedTest
            @ValueSource(strings = {"0", "100000001"})
            void 입력값이_0원_초과_100000000원_이하가_아니라면_예외를_발생_시켜야_한다(String input) {

                // when & then
                assertThatThrownBy(() -> {
                    Money.from(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.MONEY_OUT_OF_RANGE.getMessage());
            }

            @Test
            void 입력값이_10으로_나누어_떨어지지_않으면_예외를_발생_시켜야_한다() {

                // given
                String input = "10001";

                // when & then
                assertThatThrownBy(() -> {
                    Money.from(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
            }
        }
    }
}
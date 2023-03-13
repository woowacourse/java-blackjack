package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @ParameterizedTest
    @ValueSource(strings = {"y", "n"})
    @DisplayName("입력값과 Order가 가지고 있는 값이 같은지 테스트")
    void getValueTest(final String input) {
        final Order order = Order.from(input);

        assertThat(order.getValue()).isEqualTo(input);
    }

    @ParameterizedTest
    @CsvSource(value = {"y:true", "n:false"}, delimiter = ':')
    @DisplayName("입력값에 따라 isHit 메서드의 반환값이 다른지 테스트")
    void isYESTest(final String input, final boolean expected) {
        final Order order = Order.from(input);

        assertThat(order.isHit()).isEqualTo(expected);
    }
}

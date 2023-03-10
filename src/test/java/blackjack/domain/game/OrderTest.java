package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class OrderTest {

    @ParameterizedTest
    @ValueSource(strings = {"y", "n"})
    @DisplayName("입력값과 Order가 가지고 있는 값이 같은지 테스트")
    void getValueTest(final String input) {
        final Order order = Order.from(input);

        assertThat(order.getValue()).isEqualTo(input);
    }

    @Test
    @DisplayName("y를 입력하면 YES가 나오는지 테스트")
    void isYESTest() {
        final Order order = Order.YES;

        assertThat(order.isYES()).isTrue();
    }
}

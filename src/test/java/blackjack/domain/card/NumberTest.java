package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberTest {


    @DisplayName("숫자들의 합을 계산한다.")
    @Test
    void sum() {
        List<Number> numbers = List.of(Number.ACE, Number.FIVE, Number.FOUR);

        int sum = Number.sum(numbers);

        assertThat(sum).isEqualTo(10);
    }

    @DisplayName("에이스가 하나라도 있다면 에이스 하나를 11로 계산한다.")
    @Test
    void sumOneAceToSoftHand() {
        List<Number> numbers = List.of(Number.ACE, Number.FIVE, Number.FOUR);

        int sum = Number.sumOneAceToSoftHand(numbers);

        assertThat(sum).isEqualTo(20);
    }

    @DisplayName("에이스가 없는데 소프트핸드 계산법을 사용하려고 한다면 예외가 발생한다.")
    @Test
    void invalidSoftHandSumCall() {
        List<Number> numbers = List.of(Number.THREE, Number.FIVE, Number.FOUR);

        assertThatThrownBy(() -> Number.sumOneAceToSoftHand(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

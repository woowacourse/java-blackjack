package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ResultCountTest {
    @DisplayName("생성자에 매개변수를 전달하지 않으면 value 값은 0으로 초기화된다.")
    @Test
    void constructor_initsWithZero() {
        ResultCount resultCount = new ResultCount();

        int actual = resultCount.toInt();

        assertThat(actual).isZero();
    }

    @DisplayName("생성자에 int 값을 전달하면 value 는 전달된 값으로 초기화된다.")
    @ParameterizedTest(name = "[{index}] 값: {0}")
    @ValueSource(ints = {1, 10, 100})
    void constructor_withIntValue(int input) {
        // given
        ResultCount resultCount = new ResultCount(input);

        // when
        int actual = resultCount.toInt();

        // then
        assertThat(actual).isEqualTo(input);
    }

    @DisplayName("같은 value 를 가지고 있는 서로 다른 ResultCount 는 동등하다.")
    @ParameterizedTest(name = "[{index}] 값: {0}")
    @ValueSource(ints = {1, 10, 100})
    void resultCountEquality(int input) {
        // given
        ResultCount resultCount = new ResultCount(input);
        ResultCount anotherHasSameValue = new ResultCount(input);

        // when & then
        assertThat(resultCount).isEqualTo(anotherHasSameValue);
    }

    @DisplayName("increment 를 통해 값을 1씩 증가시킬 수 있다.")
    @Test
    void increment() {
        ResultCount resultCount = new ResultCount();
        for (int i = 0; i < 3; i++) {
            resultCount.increment();
        }

        int actual = resultCount.toInt();

        assertThat(actual).isEqualTo(3);
    }
}

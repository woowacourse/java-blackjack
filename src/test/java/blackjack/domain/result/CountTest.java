package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class CountTest {
    @Test
    @DisplayName("숫자 값을 통해 횟수를 생성한다.")
    public void Count_Instance_create_with_integer() {
        assertThatCode(() -> {
            final var sut = Count.valueOf(3);
            assertThat(sut.toInt()).isEqualTo(3);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("같은 숫자로 생성된 횟수는 동일하다")
    public void Count_Instance_is_value_object() {
        assertThatCode(() -> {
            final var sut = Count.valueOf(3);
            assertThat(sut).isEqualTo(new Count(3));
        }).doesNotThrowAnyException();
    }

}

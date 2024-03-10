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
            final var sut = new Count(3);
            assertThat(sut.toInt()).isEqualTo(3);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("횟수를 1 증가시킨다.")
    public void Count_increment_1_count() {

    }
}

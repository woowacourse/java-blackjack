package blackjack.domain.user.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StatusTest {
    @DisplayName("점수에 맞는 상태를 반환하는지 확인한다")
    @ParameterizedTest
    @CsvSource(value = {"21:BLACKJACK", "2:PLAYING", "30:BURST"}, delimiter = ':')
    void testOf(int score, Status status) {
        assertThat(Status.of(score)).isEqualTo(status);
    }

    @DisplayName("점수가 범위에 맞지 않으면 에러를 발생시킨다.")
    @ParameterizedTest
    @CsvSource(value = {"1", "31"})
    void testOfValidation(int score) {
        assertThatThrownBy(() -> Status.of(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수 조건에 맞는 Status가 없습니다.");
    }
}

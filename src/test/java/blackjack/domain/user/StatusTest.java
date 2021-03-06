package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {
    @DisplayName("점수에 맞는 상태를 반환하는지 확인한다")
    @ParameterizedTest
    @CsvSource(value = {"21:BLACKJACK", "2:PLAYING", "30:BURST"}, delimiter = ':')
    void testOf(int score, Status status) {
        assertThat(Status.of(score)).isEqualTo(status);
    }
}

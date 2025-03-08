package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AnswerTest {

    @DisplayName("적절한 Answer를 생성한다.")
    @ParameterizedTest
    @CsvSource({
            "y, YES",
            "n, NO"
    })
    void createAnswer(final String value, final Answer expected) {
        // given

        // when & then
        assertThat(Answer.from(value)).isEqualTo(expected);
    }

    @DisplayName("YES인지 판별한다.")
    @ParameterizedTest
    @CsvSource({
            "YES, true",
            "NO, false"
    })
    void isYes(final Answer answer, final boolean expected) {
        // given

        // when & then
        assertThat(answer.isYes()).isEqualTo(expected);
    }
}
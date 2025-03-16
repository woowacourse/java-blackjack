package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardScoreTest {

    @ParameterizedTest
    @CsvSource({
            "A, A",
            "TWO, 2"
    })
    @DisplayName("이름을 조회한다")
    void findName(final CardScore cardScore, final String score) {
        // Given

        // When & Then
        assertThat(cardScore.getName()).isEqualTo(score);
    }

    @ParameterizedTest
    @CsvSource({
            "EIGHT, 8",
            "A, 1"
    })
    @DisplayName("최소 번호를 조회한다")
    void getMinNumber(final CardScore cardScore, final int expected) {
        // Given

        // When
        assertThat(cardScore.getMinNumber()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "EIGHT, 8",
            "A, 11"
    })
    @DisplayName("최대 번호를 조회한다")
    void getMaxNumber(final CardScore cardScore, final int expected) {
        // Given

        // When & Then
        assertThat(cardScore.getMaxNumber()).isEqualTo(expected);
    }
}

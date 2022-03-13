package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberTest {

    @DisplayName("카드 이름 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"ACE, A", "TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5", "SIX, 6", "SEVEN, 7", "EIGHT, 8",
            "NINE, 9", "TEN, 10", "JACK, J", "QUEEN, Q", "KING, K"})
    void getName_AllNumbers_ReturnExactName(Number number, String name) {
        assertThat(number.getName()).isEqualTo(name);
    }

    @DisplayName("카드 점수 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"ACE, 1", "TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5", "SIX, 6", "SEVEN, 7", "EIGHT, 8",
            "NINE, 9", "TEN, 10", "JACK, 10", "QUEEN, 10", "KING, 10"})
    void getScore_AllNumbers_ReturnExactScore(Number number, int score) {
        assertThat(number.getScore()).isEqualTo(score);
    }
}

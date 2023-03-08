package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ScoreTest {

    @Test
    @DisplayName("점수 성성 테스트")
    void makeScoreTest() {
        assertDoesNotThrow(() -> new Score(10, 2));
    }

    @ParameterizedTest(name = "블랙잭인가: {2}")
    @DisplayName("블랙잭인지 확인 테스트")
    @CsvSource(value = {"19,2,false", "21,3,false", "21,2,true"})
    void blackJackConditionTest(int value, int numberOfCards, boolean expectedIsBlackJack) {
        Score score = new Score(value, numberOfCards);

        assertThat(score.isBlackJack()).isEqualTo(expectedIsBlackJack);
    }

}

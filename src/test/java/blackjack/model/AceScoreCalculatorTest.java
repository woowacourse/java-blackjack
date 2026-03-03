package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AceScoreCalculatorTest {

    AceScoreCalculator aceScoreCalculator;

    @BeforeEach
    void setUp() {
        aceScoreCalculator = new AceScoreCalculator();
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 15, 20})
    @DisplayName("ACE가 1점으로 계산되는 테스트")
    void calculateAceScoreTest1(int currentScore) {
        // when
        int aceScore = aceScoreCalculator.calculateAceScore(currentScore);

        // then
        assertThat(aceScore).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    @DisplayName("ACE가 11점으로 계산되는 테스트")
    void calculateAceScoreTest11(int currentScore) {
        // when
        int aceScore = aceScoreCalculator.calculateAceScore(currentScore);

        // then
        assertThat(aceScore).isEqualTo(11);
    }
}
package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    @Test
    @DisplayName("ACE 개수가 0개인 경우 0을 반환한다.")
    void calculateTotalAceScoreWhenAceCountIsZero() {
        assertThat(Score.calculateAceScore(0, 10)).isZero();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,10,11", "1,11,1", "2,9,12", "2,10,2", "3,8,13", "3,9,3"})
    @DisplayName("ACE의 개수와 ACE를 제외한 다른 카드의 점수에 따라 모든 ACE의 점수를 다르게 계산한다.")
    void calculateTotalAceScore(int aceCount, int otherCardsScore, int expected) {
        assertThat(Score.calculateAceScore(aceCount, otherCardsScore)).isEqualTo(expected);
    }
}

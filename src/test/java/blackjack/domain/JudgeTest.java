package blackjack.domain;

import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JudgeTest {

    @DisplayName("핸드가 건네지면 가장 최선의 합계를 구할 수 있다")
    @Test
    void testCalculateBestScore() {
        Judge judge = new Judge();

        Hand hand = HandFixture.of(1, 1);
        assertThat(judge.calculateBestScore(hand)).isEqualTo(12);
    }
}
package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCaculatorTest {
    @DisplayName("점수 계산 테스트")
    @Test
    void 점수_계산_테스트() {
        List<Card> cards = List.of(Card.of(CardNumber.J, CardShape.CLOVER), Card.of(CardNumber.Q, CardShape.CLOVER));
        assertThat(ScoreCalculator.calculate(cards)).isEqualTo(20);
    }
}

package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalculatorTest {
    @Test
    @DisplayName("카드의 숫자 합을 계산한다")
    void calculateScoreTest() {
        // given
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardJack = new Card(Figure.SPADE, Number.JACK);
        Card cardTwo = new Card(Figure.SPADE, Number.TWO);

        List<Card> cards = List.of(cardTen, cardJack);
        List<Card> cards2 = List.of(cardTen, cardTwo);

        // when
        Score score = ScoreCalculator.calculate(cards);
        Score score2 = ScoreCalculator.calculate(cards2);
        // then
        assertThat(score.getScore()).isEqualTo(20);
        assertThat(score2.getScore()).isEqualTo(12);
    }
}
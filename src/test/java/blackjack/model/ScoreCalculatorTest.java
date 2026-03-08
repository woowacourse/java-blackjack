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

    @Test
    @DisplayName("에이스가 2개일 때, 합이 21을 넘지 않도록 하나는 1로, 하나는 11로 계산한다")
    void calculateAceTwoTest() {
        // given
        List<Card> cards = List.of(
                new Card(Figure.SPADE, Number.ACE),
                new Card(Figure.HEART, Number.ACE));
        // when
        Score score = ScoreCalculator.calculate(cards);
        // then
        assertThat(score.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스가 2개여도 다른 카드와의 합이 21을 넘으면 둘 다 1로 계산한다")
    void calculateAceTwo() {
        // given
        List<Card> cards = List.of(
                new Card(Figure.HEART, Number.ACE),
                new Card(Figure.DIAMOND, Number.ACE),
                new Card(Figure.CLOVER, Number.SIX));
        // when
        Score score = ScoreCalculator.calculate(cards);
        // then
        assertThat(score.getScore()).isEqualTo(18);
    }
}
package blackjack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalculatorTest {

    ScoreCalculator scoreCalculator;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();
    }

    @Test
    @DisplayName("카드의 숫자 합을 계산한다")
    void calculateScoreTest() {
        // given
        Deck deck = new Deck();
        List<Card> cardsOne = List.of(deck.giveCard(), deck.giveCard());
        List<Card> cardsTwo = List.of(deck.giveCard(), deck.giveCard());

        // when
        int cardScoreOne = scoreCalculator.getCardScore(cardsOne);
        int cardScoreTwo = scoreCalculator.getCardScore(cardsTwo);

        // then
        assertThat(cardScoreOne).isEqualTo(13);
        assertThat(cardScoreTwo).isEqualTo(7);
    }

    @Test
    @DisplayName("21이 넘으면 버스트 처리한다.")
    void isBurstTest() {
        // given
        int score = 23;
        // when & then
        assertThat(scoreCalculator.isBurst(score)).isTrue();
    }

    @Test
    @DisplayName("21이 넘지 않으면 버스트 처리하지 않는다.")
    void isBurstTest2() {
        // given
        int score = 17;
        // when & then
        assertThat(scoreCalculator.isBurst(score)).isFalse();
    }
}
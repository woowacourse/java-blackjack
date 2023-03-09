package blackjack.domain.participant;

import blackjack.domain.card.CardNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreTest {

    @Test
    @DisplayName("점수 계산")
    void calculateScoreTest() {
        Score score = new Score();

        score.calculateScore(List.of(CardNumber.FIVE, CardNumber.NINE));

        Assertions.assertThat(score.getScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("21점 초과일 때 ACE 처리 (ACE 1장인 경우)")
    void calculateScoreIncludeAceTest() {
        Score score = new Score();

        score.calculateScore(List.of(CardNumber.FIVE, CardNumber.NINE, CardNumber.ACE));

        Assertions.assertThat(score.getScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("21점 초과일 때 ACE 처리 (ACE 3장일 경우)")
    void calculateScoreIncludeMultipleAceTest() {
        Score score = new Score();

        score.calculateScore(List.of(CardNumber.FIVE, CardNumber.NINE,
                CardNumber.ACE, CardNumber.ACE, CardNumber.ACE));

        Assertions.assertThat(score.getScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("ACE만 4장일 경우")
    void calculateScoreOnlyAceTest() {
        Score score = new Score();

        score.calculateScore(List.of(CardNumber.ACE, CardNumber.ACE, CardNumber.ACE, CardNumber.ACE));

        Assertions.assertThat(score.getScore()).isEqualTo(14);
    }
}

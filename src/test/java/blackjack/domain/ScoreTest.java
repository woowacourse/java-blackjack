package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Nested
    class calculateScore {
        final CardGroup cardGroup = new CardGroup(
                new Card(CardShape.SPADE, CardNumber.TEN),
                new Card(CardShape.SPADE, CardNumber.THREE));

        @Test
        @DisplayName("User를 넣으면 점수를 가진 Score객체를 반환하는 기능 테스트")
        void calculateScoreTest() {
            final Score score = Score.calculateScore(cardGroup);

            assertThat(score.getValue()).isEqualTo(13);
        }

        @Test
        @DisplayName("Ace를 포함하고, 21이 넘는 경우, Ace는 1로 계산된다.")
        void calculateScoreIfContainAceAndOver21Test() {
            cardGroup.add(new Card(CardShape.CLOVER, CardNumber.ACE));

            final Score score = Score.calculateScore(cardGroup);

            assertThat(score.getValue()).isEqualTo(14);
        }
    }
}

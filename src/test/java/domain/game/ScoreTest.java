package domain.game;

import static domain.FixtureCard.ACE_HEARTS;
import static domain.FixtureCard.TEN_HEARTS;
import static domain.FixtureCard.TWO_HEARTS;
import static domain.game.Score.INVALID_SCORE_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {
    @DisplayName("2이상 30 이하의 점수를 입력하면 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 30})
    void createSuccess(int value) {
        assertThatCode(() -> Score.from(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("2미만 30 초과의 점수를 입력하면 예외를 발생시킨다..")
    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    void createFail(int value) {
        assertThatCode(() -> Score.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_SCORE_MESSAGE);
    }

    @Nested
    @DisplayName("손패의 점수를 계산한다.")
    class calculateScore {

        @DisplayName("블랙잭인 경우")
        @Test
        void blackJack() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            Score score = hand.calculateScore();

            assertThat(score).isEqualTo(Score.from(21));
        }

        @DisplayName("A가 하나 있고, A를 1로 계산해야 하는 경우")
        @Test
        void oneAceOneRegard() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    TEN_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            Score score = hand.calculateScore();

            assertThat(score).isEqualTo(Score.from(21));
        }

        @DisplayName("A가 두개 이상이고, 모두 1로 계산해야 하는 경우")
        @Test
        void TwoAceTwoRegard() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    ACE_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            Score score = hand.calculateScore();

            assertThat(score).isEqualTo(Score.from(12));
        }

        @DisplayName("A가 두개 이상이고, 하나만 11로 계산해야 하는 경우")
        @Test
        void ThreeAceTwoRegard() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    ACE_HEARTS,
                    TWO_HEARTS
            );

            Hand hand = new Hand(cards);
            Score score = hand.calculateScore();

            assertThat(score).isEqualTo(Score.from(14));
        }

        @DisplayName("A가 없는 경우 - 버스트")
        @Test
        void NoAceBust() {
            List<Card> cards = List.of(
                    TEN_HEARTS,
                    TEN_HEARTS,
                    TWO_HEARTS
            );

            Hand hand = new Hand(cards);
            Score score = hand.calculateScore();

            assertThat(score).isEqualTo(Score.from(22));
        }

        @DisplayName("A가 없는 경우 - 버스트아님")
        @Test
        void NoAceNotBust() {
            List<Card> cards = List.of(
                    TEN_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            Score score = hand.calculateScore();

            assertThat(score).isEqualTo(Score.from(20));
        }

        @DisplayName("A가 있으면서, A를 1로 생각해도 버스트가 나는 경우")
        @Test
        void HasAceBust() {
            List<Card> cards = List.of(
                    ACE_HEARTS,
                    TWO_HEARTS,
                    TEN_HEARTS,
                    TEN_HEARTS
            );

            Hand hand = new Hand(cards);
            Score score = hand.calculateScore();

            assertThat(score).isEqualTo(Score.from(23));
        }
    }

    @DisplayName("Score를 통해 대소비교를 한다.")
    @Test
    void compareToByScore() {
        Score score1 = Score.from(20);
        Score score2 = Score.from(19);

        boolean actual = score1.compareTo(score2) > 0;

        assertThat(actual).isTrue();
    }
}
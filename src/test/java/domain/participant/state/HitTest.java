package domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TrumpCard;
import domain.participant.state.hand.Hand;
import domain.participant.state.hand.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HitTest {

    @Nested
    class ValidCases {

        @DisplayName("Hit 상태에서는 카드를 추가로 받을 수 있다.")
        @Test
        void draw() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.SIX_OF_CLUBS,
                    TrumpCard.THREE_OF_HEARTS
            ));
            Hit hit = new Hit(hand, Score.TWENTY_ONE);

            // when
            State newState = hit.draw(TrumpCard.FIVE_OF_SPADES);

            // then
            assertThat(newState.retrieveCards()).hasSize(3);
        }

        @DisplayName("Hit 상태에서 점수가 BUST가 되면 Bust 상태로 변경된다.")
        @Test
        void drawChangesToBust() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS
            ));
            Hit hit = new Hit(hand, Score.TWENTY_ONE);

            // when
            State newState = hit.draw(TrumpCard.THREE_OF_CLUBS);

            // then
            assertThat(newState).isInstanceOf(Bust.class);
        }

        @DisplayName("Hit 상태에서 점수가 limitScore보다 낮으면 그대로 Hit 상태를 유지한다.")
        @Test
        void drawMaintainsHit() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.FOUR_OF_CLUBS,
                    TrumpCard.TWO_OF_HEARTS
            ));
            Hit hit = new Hit(hand, Score.TWENTY_ONE);

            // when
            State newState = hit.draw(TrumpCard.FIVE_OF_DIAMONDS);

            // then
            assertThat(newState).isInstanceOf(Hit.class);
        }

        @DisplayName("Hit 상태에서 점수가 limitScore 이상이면 Stay 상태로 변경된다.")
        @Test
        void drawChangesToStay() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.NINE_OF_SPADES,
                    TrumpCard.NINE_OF_CLUBS
            ));
            Hit hit = new Hit(hand, Score.SEVENTEEN);

            // when
            State newState = hit.draw(TrumpCard.TWO_OF_DIAMONDS);

            // then
            assertThat(newState).isInstanceOf(Stay.class);
        }

        @DisplayName("Hit 상태에서는 히트할 수 있다.")
        @Test
        void canHit() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.SEVEN_OF_DIAMONDS,
                    TrumpCard.TWO_OF_SPADES
            ));
            Hit hit = new Hit(hand, Score.TWENTY_ONE);

            // when & then
            assertThat(hit.canHit()).isTrue();
        }

        @DisplayName("Hit 상태에서 점수를 계산해야 한다.")
        @Test
        void calculateScore() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.EIGHT_OF_SPADES,
                    TrumpCard.FIVE_OF_HEARTS
            ));
            Hit hit = new Hit(hand, Score.TWENTY_ONE);

            // when
            Score score = hit.calculateScore();

            // then
            assertThat(score).isEqualTo(Score.from(hand.getCards()));
        }

        @DisplayName("Hit 상태에서 카드 목록을 반환해야 한다.")
        @Test
        void retrieveCards() {
            // given
            List<TrumpCard> initialCards = List.of(
                    TrumpCard.EIGHT_OF_SPADES,
                    TrumpCard.FIVE_OF_HEARTS
            );
            Hand hand = new Hand(initialCards);
            Hit hit = new Hit(hand, Score.TWENTY_ONE);

            // when
            List<TrumpCard> retrievedCards = hit.retrieveCards();

            // then
            assertThat(retrievedCards).isEqualTo(initialCards);
        }
    }
}

package domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.TrumpCard;
import domain.participant.state.hand.Hand;
import domain.participant.state.hand.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StayTest {

    @Nested
    class ValidCases {

        @DisplayName("Stay 상태에서는 카드를 더 받을 수 없다.")
        @Test
        void draw() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS
            ));
            Stay stay = new Stay(hand);

            // when & then
            assertThatThrownBy(() -> stay.draw(TrumpCard.FIVE_OF_DIAMONDS))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 끝난 상태에서는 카드를 뽑을 수 없습니다.");
        }

        @DisplayName("Stay 상태에서는 히트 할 수 없다.")
        @Test
        void canHit() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS
            ));
            Stay stay = new Stay(hand);

            // when & then
            assertThat(stay.canHit()).isFalse();
        }

        @DisplayName("Stay 상태에서 점수를 계산해야 한다.")
        @Test
        void calculateScore() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS
            ));
            Stay stay = new Stay(hand);

            // when
            Score score = stay.calculateScore();

            // then
            assertThat(score).isEqualTo(Score.from(hand.getCards()));
        }

        @DisplayName("Stay 상태에서 카드 목록을 반환해야 한다.")
        @Test
        void retrieveCards() {
            // given
            List<TrumpCard> initialCards = List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS
            );
            Hand hand = new Hand(initialCards);
            Stay stay = new Stay(hand);

            // when
            List<TrumpCard> retrievedCards = stay.retrieveCards();

            // then
            assertThat(retrievedCards).isEqualTo(initialCards);
        }
    }
}

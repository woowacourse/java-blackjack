package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BustTest {

    @Nested
    class ValidCases {

        @DisplayName("Bust 상태에서는 카드를 더 받을 수 없다.")
        @Test
        void draw() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS,
                    TrumpCard.FOUR_OF_CLUBS
            ));
            Bust bust = new Bust(hand);

            // when & then
            assertThatThrownBy(() -> bust.draw(TrumpCard.FIVE_OF_DIAMONDS))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 끝난 상태에서는 카드를 뽑을 수 없습니다.");
        }

        @DisplayName("Bust 상태에서 히트할 수 없다.")
        @Test
        void canHit() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS,
                    TrumpCard.FOUR_OF_CLUBS
            ));
            Bust bust = new Bust(hand);

            // when & then
            assertThat(bust.canHit()).isFalse();
        }

        @DisplayName("점수를 계산해야 한다.")
        @Test
        void calculateScore() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS,
                    TrumpCard.FOUR_OF_CLUBS
            ));
            Bust bust = new Bust(hand);

            // when
            Score score = bust.calculateScore();

            // then
            assertThat(score).isEqualTo(Score.BUST);
        }

        @DisplayName("카드 목록을 반환해야 한다.")
        @Test
        void retrieveCards() {
            // given
            List<TrumpCard> initialCards = List.of(
                    TrumpCard.TEN_OF_SPADES,
                    TrumpCard.NINE_OF_HEARTS,
                    TrumpCard.FOUR_OF_CLUBS
            );
            Hand hand = new Hand(initialCards);
            Bust bust = new Bust(hand);

            // when
            List<TrumpCard> retrievedCards = bust.retrieveCards();

            // then
            assertThat(retrievedCards).isEqualTo(initialCards);
        }
    }

    @Nested
    class InValidCases {

        @DisplayName("점수가 버스트여야 한다.")
        @Test
        void validateScore() {
            // given
            Hand hand = new Hand(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.KING_OF_HEARTS));

            // when & then
            assertThatThrownBy(() -> new Bust(hand))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("버스트여야 합니다.");
        }
    }
}

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

class BlackJackTest {

    @Nested
    class ValidCases {

        @DisplayName("Blackjack 상태에서는 카드를 더 받을 수 없다.")
        @Test
        void draw() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.KING_OF_HEARTS
            ));
            BlackJack blackjack = new BlackJack(hand);

            // when & then
            assertThatThrownBy(() -> blackjack.draw(TrumpCard.FIVE_OF_DIAMONDS))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("이미 끝난 상태에서는 카드를 뽑을 수 없습니다.");
        }

        @DisplayName("Blackjack 상태에서 하트 할 수 없다.")
        @Test
        void canHit() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.KING_OF_HEARTS
            ));
            BlackJack blackjack = new BlackJack(hand);

            // when & then
            assertThat(blackjack.canHit()).isFalse();
        }

        @DisplayName("Blackjack 상태에서 점수를 계산해야 한다.")
        @Test
        void calculateScore() {
            // given
            Hand hand = new Hand(List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.KING_OF_HEARTS
            ));
            BlackJack blackjack = new BlackJack(hand);

            // when
            Score score = blackjack.calculateScore();

            // then
            assertThat(score).isEqualTo(Score.BLACKJACK);
        }

        @DisplayName("Blackjack 상태에서 카드 목록을 반환해야 한다.")
        @Test
        void retrieveCards() {
            // given
            List<TrumpCard> initialCards = List.of(
                    TrumpCard.ACE_OF_SPADES,
                    TrumpCard.KING_OF_HEARTS
            );
            Hand hand = new Hand(initialCards);
            BlackJack blackjack = new BlackJack(hand);

            // when
            List<TrumpCard> retrievedCards = blackjack.retrieveCards();

            // then
            assertThat(retrievedCards).isEqualTo(initialCards);
        }
    }

    @Nested
    class InValidCases {

        @DisplayName("점수가 블랙잭이여야 한다.")
        @Test
        void validateScore() {
            // given
            Hand hand = new Hand(List.of(TrumpCard.FIVE_OF_CLUBS, TrumpCard.KING_OF_HEARTS));

            // when & then
            assertThatThrownBy(() -> new BlackJack(hand))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("블랙잭이여야 합니다.");
        }
    }
}

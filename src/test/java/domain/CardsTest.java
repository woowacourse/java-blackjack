package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardsTest {
    private Cards createCardsWithCards(Card... cards) {
        Cards result = new Cards();
        for (Card card : cards) {
            result.addCard(card);
        }
        return result;
    }

    @Nested
    class AddCardTest {
        @Test
        @DisplayName("카드 합계가 21점 미만일 때 카드를 정상적으로 추가할 수 있다.")
        void shouldAddCardWhenDeckSumLessThanMaximum() {
            // given
            Cards cards = new Cards();
            cards.addCard(new Card(CardShape.SPADE, CardContents.TWO));

            // when
            cards.addCard(new Card(CardShape.HEART, CardContents.THREE));

            // then
            assertThat(cards.getCards()).hasSize(2);
        }

        @Test
        @DisplayName("카드 합계가 21점일 때 카드를 추가로 뽑으면 예외가 발생한다.")
        void shouldThrowExceptionWhenDeckSumEqualsMaximum() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.TEN),
                    new Card(CardShape.DIAMOND, CardContents.A)
            );

            Card newCard = new Card(CardShape.HEART, CardContents.THREE);

            // when & then
            assertThatThrownBy(() -> cards.addCard(newCard))
                    .isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("카드 합계가 21점을 초과할 때 카드를 추가로 뽑으면 예외가 발생한다.")
        void shouldThrowExceptionWhenDeckSumOverMaximum() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.TEN),
                    new Card(CardShape.DIAMOND, CardContents.TWO)
            );

            Card newCard = new Card(CardShape.HEART, CardContents.THREE);

            // when & then
            assertThatThrownBy(() -> cards.addCard(newCard))
                    .isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("카드 목록에 이미 존재하는 카드를 중복으로 추가하면 예외가 발생한다.")
        void shouldThrowExceptionForDuplicatedCard() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.DIAMOND, CardContents.TWO)
            );

            Card duplicatedCard = new Card(CardShape.DIAMOND, CardContents.TWO);

            // when & then
            assertThatThrownBy(() -> cards.addCard(duplicatedCard))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class CalculateCardScoreSumTest {
        @Test
        @DisplayName("Ace가 없는 경우 카드 점수의 합을 정확히 계산한다.")
        void shouldReturnScoreSumWithoutAce() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.TEN)
            );

            // when & then
            assertThat(cards.calculateCardScoreSum()).isEqualTo(20);
        }

        @Test
        @DisplayName("Ace가 포함되어 있고 11점으로 계산해도 버스트가 나지 않는다면, Ace를 11점으로 계산한다.")
        void shouldReturnScoreSumWithAceCalculatedAsElevenWhenNotBust() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.A)
            );

            // when & then
            assertThat(cards.calculateCardScoreSum()).isEqualTo(21);
        }

        @Test
        @DisplayName("Ace가 포함되어 있으나 11점으로 계산 시 버스트가 난다면, Ace를 1점으로 계산한다.")
        void shouldReturnScoreSumWithAceCalculatedAsOneWhenElevenCausesBust() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.A)
            );

            // when & then
            assertThat(cards.calculateCardScoreSum()).isEqualTo(21);
        }

        @Test
        @DisplayName("Ace가 2장 이상일 때, 최대 1장만 11점으로 계산하고 나머지는 1점으로 계산한다.")
        void shouldReturnScoreSumWithMultiplyAces() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.A),
                    new Card(CardShape.HEART, CardContents.A),
                    new Card(CardShape.CLOVER, CardContents.A),
                    new Card(CardShape.DIAMOND, CardContents.TEN)
            );

            // when & then
            assertThat(cards.calculateCardScoreSum()).isEqualTo(13);
        }
    }

    @Nested
    class IsLessThanMaxScoreTest {
        @Test
        @DisplayName("카드의 합이 21점 미만이면 true를 반환한다.")
        void shouldReturnTrueWhenDeckSumLessThanMaximum() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.TEN)
            );

            // when & then
            assertTrue(cards.isLessThanMaxScore());
        }

        @Test
        @DisplayName("카드의 합이 정확히 21점이면 false를 반환한다.")
        void shouldReturnFalseWhenDeckSumEqualsMaximum() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.A)
            );

            // when & then
            assertFalse(cards.isLessThanMaxScore());
        }

        @Test
        @DisplayName("카드의 합이 21점을 초과하면 false를 반환한다.")
        void shouldReturnFalseWhenDeckSumOverMaximum() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.TWO)
            );

            // when & then
            assertFalse(cards.isLessThanMaxScore());
        }
    }

    @Nested
    class IsBustTest {
        @Test
        @DisplayName("카드의 합이 21을 초과하면 버스트로 판정한다.")
        void shouldReturnTrueWhenDeckSumOverMaximum() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.TEN)
            );

            // when & then
            assertTrue(cards.isBust());
        }

        @Test
        @DisplayName("카드의 합이 21이하라면 버스트로 판정하지 않는다.")
        void shouldReturnFalseWhenDeckSumEqualsMaximumOrLess() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.A)
            );

            // when & then
            assertFalse(cards.isBust());
        }
    }

    @Nested
    class IsBlackJackTest {
        @Test
        @DisplayName("카드가 두장이고, 카드의 합이 21일 때만 블랙잭으로 판정한다.")
        void shouldReturnTrueWhenTwoCardsSumEqualsTwentyOne() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.A)
            );

            // when & then
            assertTrue(cards.isBlackJack());
        }

        @Test
        @DisplayName("카드가 두장 이상이고, 카드의 합이 21이라면 블랙잭으로 판정하지 않는다.")
        void shouldReturnTrueWhenMoreThanTwoCardsSumEqualsTwentyOne() {
            // given
            Cards cards = createCardsWithCards(
                    new Card(CardShape.SPADE, CardContents.FIVE),
                    new Card(CardShape.HEART, CardContents.FIVE),
                    new Card(CardShape.HEART, CardContents.A)
            );

            // when & then
            assertFalse(cards.isBlackJack());
        }
    }
}

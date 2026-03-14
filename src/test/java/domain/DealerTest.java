package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerTest {
    private Dealer createDealerWithCards(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }
        return dealer;
    }

    @Test
    @DisplayName("딜러는 초기 카드 내역 공개 시, 가지고 있는 카드 2장 중 한장만 보여준다.")
    void shouldReturnSingleCardForInitialVisibleCards() {
        // given
        Card card1 = new Card(CardShape.HEART, CardContents.FIVE);
        Card card2 = new Card(CardShape.HEART, CardContents.SIX);
        Dealer testDealer = createDealerWithCards(card1, card2);

        // when & then
        assertThat(testDealer.getInitialVisibleCards())
                .containsExactly(card1);
    }

    @Nested
    class IsDrawableTest {
        @Test
        @DisplayName("딜러는 카드의 합이 16 미만이면 카드를 더 뽑을 수 있다.")
        void shouldReturnTrueWhenCardScoreSumLessThanMinimum() {
            // given
            Dealer testDealer = createDealerWithCards(
                    new Card(CardShape.HEART, CardContents.FIVE),
                    new Card(CardShape.HEART, CardContents.SIX)
            );

            // when & then
            assertTrue(testDealer.isDrawable());
        }

        @Test
        @DisplayName("딜러는 카드의 합이 16이면 카드를 더 뽑을 수 있다.")
        void shouldReturnTrueWhenCardScoreSumEqualsMinimum() {
            // given
            Dealer testDealer = createDealerWithCards(
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.SIX)
            );

            // when & then
            assertTrue(testDealer.isDrawable());
        }

        @Test
        @DisplayName("딜러는 카드의 합이 16을 초과하면 카드를 더 이상 뽑을 수 없다.")
        void shouldReturnFalseWheCardScoreSumOverMinimum() {
            // given
            Dealer testDealer = createDealerWithCards(
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.SIX),
                    new Card(CardShape.SPADE, CardContents.FIVE)
            );

            // when & then
            assertFalse(testDealer.isDrawable());
        }
    }

    @Nested
    class AddCardTest {
        @Test
        @DisplayName("딜러는 카드의 합이 16 미만일 때 카드를 정상적으로 추가할 수 있다.")
        void shouldAddCardWhenDeckSumLessThanMinimum() {
            // given
            Dealer testDealer = createDealerWithCards(
                    new Card(CardShape.HEART, CardContents.FIVE),
                    new Card(CardShape.HEART, CardContents.SIX)
            );
            Card newCard = new Card(CardShape.CLOVER, CardContents.SIX);

            // when
            testDealer.addCard(newCard);

            // then
            List<Card> cards = testDealer.getCards();
            assertThat(cards).hasSize(3);
            assertThat(cards).contains(newCard);
        }

        @Test
        @DisplayName("딜러는 카드의 합이 16일 때 카드를 정상적으로 추가할 수 있다.")
        void shouldAddCardWhenDeckSumEqualsMinimum() {
            // given
            Dealer testDealer = createDealerWithCards(
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.SIX)
            );
            Card newCard = new Card(CardShape.CLOVER, CardContents.SIX);

            // when
            testDealer.addCard(newCard);

            // then
            List<Card> cards = testDealer.getCards();
            assertThat(cards).hasSize(3);
            assertThat(cards).contains(newCard);
        }

        @Test
        @DisplayName("딜러는 카드의 합이 16을 초과하면 카드를 더 이상 뽑을 수 없다.")
        void shouldThrowExceptionWhenDeckSumOverMinimum() {
            // given
            Dealer testDealer = createDealerWithCards(
                    new Card(CardShape.HEART, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.EIGHT)
            );
            Card newCard = new Card(CardShape.CLOVER, CardContents.SIX);

            // when & then
            assertThatThrownBy(() -> testDealer.addCard(newCard))
                    .isInstanceOf(IllegalStateException.class);
        }
    }
}

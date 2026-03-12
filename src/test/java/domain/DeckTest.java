package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import common.ErrorMessage;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @DisplayName("전체 덱 생성 시 52장의 카드가 들어있는 카드 덱이 생성된다.")
    void shouldReturnTotalDeckWithAllCards() {
        // given
        CardShuffleStrategy fixedCardShuffleStrategy = new CardShuffleStrategy() {
            @Override
            public void shuffle(List<Card> cards) {
            }
        };
        int expected = 52;

        // when
        Deck totalDeck = Deck.createTotalDeckAndShuffle(fixedCardShuffleStrategy);
        List<Card> cards = totalDeck.getCards();
        int result = cards.size();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Deck에서 카드를 한장 뽑으면 해당 카드를 리턴하고, Deck에서 카드가 한장 제거된다.")
    void shouldReturnSingleCardAndRemoveCardFromDeck() {
        // given
        Card expected = new Card(CardShape.SPADE, CardContents.J);
        Deck deck = new Deck();
        deck.addCard(expected);

        // when
        Card result = deck.drawCard();

        // then
        assertThat(result).isEqualTo(expected);
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
    }

    @Nested
    class IsLessThanMaxScoreTest {
        @Test
        @DisplayName("카드의 합이 21점 미만이면 true를 반환한다.")
        void shouldReturnTrueWhenDeckSumLessThanMaximum() {
            // given
            Deck deck = new Deck();
            deck.addCard(new Card(CardShape.SPADE, CardContents.TEN));
            deck.addCard(new Card(CardShape.HEART, CardContents.TEN));

            // when & then
            assertTrue(deck.isLessThanMaxScore());
        }

        @Test
        @DisplayName("카드의 합이 정확히 21점이면 false를 반환한다.")
        void shouldReturnFalseWhenDeckSumEqualsMaximum() {
            // given
            Deck deck = new Deck();
            deck.addCard(new Card(CardShape.SPADE, CardContents.TEN));
            deck.addCard(new Card(CardShape.HEART, CardContents.TEN));
            deck.addCard(new Card(CardShape.CLOVER, CardContents.A));

            // when & then
            assertFalse(deck.isLessThanMaxScore());
        }

        @Test
        @DisplayName("카드의 합이 21점을 초과하면 false를 반환한다.")
        void shouldReturnFalseWhenDeckSumOverMaximum() {
            // given
            Deck deck = new Deck();
            deck.addCard(new Card(CardShape.SPADE, CardContents.TEN));
            deck.addCard(new Card(CardShape.HEART, CardContents.TEN));
            deck.addCard(new Card(CardShape.CLOVER, CardContents.TWO));

            // when & then
            assertFalse(deck.isLessThanMaxScore());
        }
    }

    @Nested
    class IsBustTest {
        @Test
        @DisplayName("카드의 합이 21을 초과하면 버스트로 판정한다.")
        void shouldReturnTrueWhenDeckSumOverMaximum() {
            // given
            Deck deck = new Deck();
            deck.addCard(new Card(CardShape.SPADE, CardContents.TEN));
            deck.addCard(new Card(CardShape.HEART, CardContents.TEN));
            deck.addCard(new Card(CardShape.CLOVER, CardContents.TEN));

            // when & then
            assertTrue(deck.isBust());
        }

        @Test
        @DisplayName("카드의 합이 21이하라면 버스트로 판정하지 않는다.")
        void shouldReturnFalseWhenDeckSumEqualsMaximumOrLess() {
            // given
            Deck deck = new Deck();
            deck.addCard(new Card(CardShape.SPADE, CardContents.TEN));
            deck.addCard(new Card(CardShape.HEART, CardContents.TEN));
            deck.addCard(new Card(CardShape.HEART, CardContents.A));

            // when & then
            assertFalse(deck.isBust());
        }
    }
}

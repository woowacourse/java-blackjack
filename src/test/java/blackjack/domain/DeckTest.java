package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    List<Card> cards;

    @BeforeEach
    void init() {
        cards = Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardRank.values())
                        .map(rank -> new Card(suit, rank)))
                .toList();
    }

    @DisplayName("카드는 섞일 수 있다.")
    @Test
    void test3() {
        // given
        Deck deck = Deck.shuffled(cards);

        // then
        assertThat(deck.getCards()).containsExactlyInAnyOrderElementsOf(cards);
    }

    @DisplayName("카드뽑을 때")
    @Nested
    class DrawCard {

        @DisplayName("시작 카드는 2개를 뽑는다")
        @Test
        void test1() {
            // given
            Deck deck = Deck.shuffled(cards);
            int expect = 2;

            // when
            List<Card> takeCards = deck.startingHand();

            // then
            assertThat(takeCards).hasSize(expect);
        }

        @DisplayName("카드 한 장을 뽑는다")
        @Test
        void test2() {
            // given
            Deck deck = Deck.shuffled(cards);
            int expect = 1;

            // when
            List<Card> takeCards = deck.hit();

            // then
            assertThat(takeCards).hasSize(expect);
        }

        @DisplayName("카드가 소진되면 예외를 던진다.")
        @Test
        void test5() {
            Deck deck = Deck.shuffled(List.of());

            assertThatThrownBy(deck::startingHand).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.EMPTY_DECK_SIZE.getMessage());
        }
    }
}

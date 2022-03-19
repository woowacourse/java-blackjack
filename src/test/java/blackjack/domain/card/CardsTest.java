package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static blackjack.domain.fixture.CardFixture.*;
import static blackjack.domain.fixture.FixedSequenceDeck.generateDeck;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Cards 클래스")
class CardsTest {

    @Test
    @DisplayName("현재 점수를 계산한다")
    void testGetScore() {
        Deck deck = generateDeck(SPADE_JACK, SPADE_TWO);
        Cards cards = deck.initialDraw();

        assertThat(cards.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산해도 21점을 초과하지 않는 경우 11점으로 계산한다")
    void testGetScoreWhenContainsAce1() {
        Deck deck = generateDeck(SPADE_ACE, SPADE_TWO);
        Cards cards = deck.initialDraw();

        assertThat(cards.calculateScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산했을 때 21점이 초과하는 경우 1점으로 계산한다")
    void testGetScoreWhenContainsAce2() {
        Deck deck = generateDeck(SPADE_ACE, SPADE_ACE, SPADE_ACE,SPADE_ACE);
        Cards cards = deck.initialDraw();
        cards.add(deck.draw());
        cards.add(deck.draw());

        assertThat(cards.calculateScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산했을 때 21점이 초과하는 경우 1점으로 계산한다. 순서에 영향을 받지 않아야한다")
    void testGetScoreWhenContainsAce3() {
        Deck deck = generateDeck(SPADE_ACE, SPADE_NINE, SPADE_TWO);
        Cards cards = deck.initialDraw();
        cards.add(deck.draw());

        assertThat(cards.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("21점을 초과하면 버스트이다")
    void testIsBust() {
        // given
        Deck deck = generateDeck(SPADE_JACK, SPADE_JACK, SPADE_TWO);

        Cards cards = deck.initialDraw();

        // when
        boolean expectedFalse = cards.isBust();
        cards.add(deck.draw());
        boolean expectedTrue = cards.isBust();

        // then
        assertThat(expectedFalse).isFalse();
        assertThat(expectedTrue).isTrue();
    }

    @Nested
    @DisplayName("isBlackjack 메서드는")
    class Describe_isBlackjack {

        @Test
        @DisplayName("첫 드로우에 21점을 달성하면 참을 반환한다")
        void returnTrueWhenBlackjack() {
            Deck deck = generateDeck(SPADE_JACK, SPADE_ACE);
            Cards cards = deck.initialDraw();

            assertThat(cards.isBlackjack()).isTrue();
        }

        @Test
        @DisplayName("21점이지만 첫 드로우에 달성한 점수가 아닌 경우 거짓을 반환한다")
        void returnFalseWhenNotBlackjack1() {
            Deck deck = generateDeck(SPADE_JACK, SPADE_JACK, SPADE_ACE);
            Cards cards = deck.initialDraw();
            cards.add(deck.draw());

            assertThat(cards.isBlackjack()).isFalse();
        }

        @Test
        @DisplayName("21미만인 경우 거짓을 반환한다")
        void returnFalseWhenNotBlackjack2() {
            Deck deck = generateDeck(SPADE_JACK, SPADE_JACK);
            Cards cards = deck.initialDraw();

            assertThat(cards.isBlackjack()).isFalse();
        }
    }
}

package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Symbol.*;
import static blackjack.domain.fixture.FixedSequenceDeck.generateDeck;
import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @Test
    @DisplayName("현재 점수를 계산한다")
    void testGetScore() {
        Deck deck = generateDeck(new Card(DIAMOND, SIX), new Card(DIAMOND, SEVEN));
        Cards cards = deck.initialDraw();

        assertThat(cards.calculateScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산해도 21점을 초과하지 않는 경우 11점으로 계산한다")
    void testGetScoreWhenContainsAce1() {
        Deck deck = generateDeck(new Card(DIAMOND, ACE), new Card(DIAMOND, TWO));
        Cards cards = deck.initialDraw();

        assertThat(cards.calculateScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산했을 때 21점이 초과하는 경우 1점으로 계산한다")
    void testGetScoreWhenContainsAce2() {
        Deck deck = generateDeck(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(HEART, ACE),new Card(CLOVER, ACE));
        Cards cards = deck.initialDraw();
        cards.add(deck.draw());
        cards.add(deck.draw());

        assertThat(cards.calculateScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산했을 때 21점이 초과하는 경우 1점으로 계산한다. 순서에 영향을 받지 않아야한다")
    void testGetScoreWhenContainsAce3() {
        Deck deck = generateDeck(new Card(DIAMOND, ACE), new Card(SPADE, NINE), new Card(HEART, TWO));
        Cards cards = deck.initialDraw();
        cards.add(deck.draw());

        assertThat(cards.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("21점을 초과하면 버스트이다")
    void testIsBust() {
        // given
        Deck deck = generateDeck(new Card(SPADE, JACK), new Card(SPADE, QUEEN), new Card(SPADE, TWO));

        Cards cards = deck.initialDraw();

        // when
        boolean expectedFalse = cards.isBust();
        cards.add(deck.draw());
        boolean expectedTrue = cards.isBust();

        // then
        assertThat(expectedFalse).isFalse();
        assertThat(expectedTrue).isTrue();
    }
}

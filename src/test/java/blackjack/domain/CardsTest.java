package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.Denomination.*;
import static blackjack.domain.Symbol.*;
import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @Test
    @DisplayName("현재 점수를 계산한다")
    void testGetScore() {
        Cards cards = new Cards();
        cards.add(new Card(DIAMOND, SIX));
        cards.add(new Card(DIAMOND, SEVEN));

        assertThat(cards.getScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산해도 21점을 초과하지 않는 경우 11점으로 계산한다")
    void testGetScoreWhenContainsAce1() {
        Cards cards = new Cards();
        cards.add(new Card(DIAMOND, ACE));

        assertThat(cards.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산했을 때 21점이 초과하는 경우 1점으로 계산한다")
    void testGetScoreWhenContainsAce2() {
        Cards cards = new Cards();
        cards.add(new Card(DIAMOND, ACE));
        cards.add(new Card(SPADE, ACE));
        cards.add(new Card(HEART, ACE));
        cards.add(new Card(CLOVER, ACE));

        assertThat(cards.getScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("Ace를 11점으로 계산했을 때 21점이 초과하는 경우 1점으로 계산한다. 순서에 영향을 받지 않아야한다")
    void testGetScoreWhenContainsAce3() {
        Cards cards = new Cards();
        cards.add(new Card(DIAMOND, ACE));
        cards.add(new Card(SPADE, NINE));
        cards.add(new Card(HEART, TWO));

        assertThat(cards.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("21점을 초과하면 버스트이다")
    void testIsBust() {
        // given
        Cards cards = new Cards();
        cards.add(new Card(SPADE, JACK));
        cards.add(new Card(SPADE, QUEEN));

        // when
        boolean expectedFalse = cards.isBust();
        cards.add(new Card(SPADE, TWO));
        boolean expectedTrue = cards.isBust();

        // then
        assertThat(expectedFalse).isFalse();
        assertThat(expectedTrue).isTrue();
    }
}

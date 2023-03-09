package blackjack.domain.card;

import blackjack.domain.game.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class HandTest {
    @Test
    @DisplayName("패 생성 테스트")
    void constructCardsTest() {
        assertThatNoException().isThrownBy(() -> new Hand(new ArrayList<>()));
    }

    @Test
    @DisplayName("카드를 추가한다")
    void addCardsTest() {
        // given
        final Hand cards = new Hand(new ArrayList<>());
        final Card card = new Card(Shape.CLOVER, Letter.ACE);
        final List<Card> expectedCards = List.of(card);

        // when
        cards.add(card);

        // that
        assertThat(expectedCards).isEqualTo(cards.getCards());
    }

    @Test
    @DisplayName("카드의 총 합을 반환한다")
    void calculateTotalScoreTest() {
        // given
        final Hand cards = new Hand(new ArrayList<>());
        final Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        final Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        final Score expected = new Score(21);

        // when
        cards.add(card1);
        cards.add(card2);

        // that
        assertThat(cards.getScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("패를 반환한다")
    void getCardsTest() {
        // given
        final Hand cards = new Hand(new ArrayList<>());
        final Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        final Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        // when
        cards.add(card1);
        cards.add(card2);

        // that
        assertThat(cards.getCards()).contains(card1, card2);
    }

    @Test
    @DisplayName("에이스 2장 일때 합 12가 되는지 테스트")
    void calculateTotalOver21Test() {
        // given
        final Hand cards = new Hand(new ArrayList<>());
        final Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        final Card card2 = new Card(Shape.DIAMOND, Letter.ACE);
        final Score expected = new Score(12);

        // when
        cards.add(card1);
        cards.add(card2);

        // that
        assertThat(cards.getScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("에이스 2장에 기본 19 일때 합 21가 되는지 테스트")
    void calculateTotalOver21Test2() {
        // given
        final Hand cards = new Hand(new ArrayList<>());
        final Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        final Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        final Card card3 = new Card(Shape.CLOVER, Letter.ACE);
        final Card card4 = new Card(Shape.CLOVER, Letter.NINE);
        final Score expected = new Score(21);

        // when
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        // that
        assertThat(cards.getScore()).isEqualTo(expected);
    }
}

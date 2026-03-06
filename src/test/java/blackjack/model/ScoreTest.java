package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("카드의 숫자 합을 계산한다")
    void calculateScoreTest() {
        // given
        Deck deck = new Deck();
        Score score = new Score();
        Score score1 = new Score();
        List<Card> cardsOne = List.of(deck.giveCard(), deck.giveCard());
        List<Card> cardsTwo = List.of(deck.giveCard(), deck.giveCard());

        // when
        score.calculateAll(cardsOne);
        score1.calculateAll(cardsTwo);

        // then
        assertThat(score.getScore()).isEqualTo(13);
        assertThat(score1.getScore()).isEqualTo(7);
    }

    @Test
    @DisplayName("21이 넘으면 버스트 처리한다.")
    void isBurstTest() {
        // given
        Card card = new Card(Figure.SPADE, Number.KING);
        Card card2 = new Card(Figure.CLOVER, Number.KING);
        Card card3 = new Card(Figure.DIAMOND, Number.KING);
        Score score = new Score();
        // when & then
        score.calculateAll(List.of(card, card2, card3));
        assertThat(score.isBurst()).isTrue();
    }

    @Test
    @DisplayName("21이 넘지 않으면 버스트 처리하지 않는다.")
    void isBurstTest2() {
        // given
        Card card = new Card(Figure.SPADE, Number.KING);
        Card card2 = new Card(Figure.CLOVER, Number.KING);
        Score score = new Score();
        // when & then
        score.calculateAll(List.of(card, card2));
        assertThat(score.isBurst()).isFalse();
    }
}
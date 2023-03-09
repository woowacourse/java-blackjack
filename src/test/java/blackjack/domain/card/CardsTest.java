package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardsTest {
    @Test
    @DisplayName("패 생성 테스트")
    void constructCardsTest() {
        assertThatNoException().isThrownBy(() -> new Cards());
    }

    @Test
    @DisplayName("카드를 추가한다")
    void addCardsTest() {
        // given
        Cards cards = new Cards();
        Card card = new Card(Shape.CLOVER, Letter.ACE);
        List<Card> expectedCards = List.of(card);

        // when
        cards.add(card);

        // that
        assertThat(expectedCards).isEqualTo(cards.getCards());
    }

    @Test
    @DisplayName("카드의 총 합을 반환한다")
    void calculateTotalScoreTest() {
        // given
        Cards cards = new Cards();
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        int expectedValue = card1.getScore() + card2.getScore();

        // when
        cards.add(card1);
        cards.add(card2);

        // that
        assertThat(expectedValue).isEqualTo(cards.calculateTotalScore());
    }

    @Test
    @DisplayName("패를 반환한다")
    void getCardsTest() {
        // given
        Cards cards = new Cards();
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

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
        Cards cards = new Cards();
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.ACE);

        // when
        cards.add(card1);
        cards.add(card2);

        // that
        assertThat(cards.calculateTotalScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 2장에 기본 19 일때 합 21가 되는지 테스트")
    void calculateTotalOver21Test2() {
        // given
        Cards cards = new Cards();
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        Card card3 = new Card(Shape.CLOVER, Letter.ACE);
        Card card4 = new Card(Shape.CLOVER, Letter.NINE);
        // when
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        // that
        assertThat(cards.calculateTotalScore()).isEqualTo(21);
    }
}

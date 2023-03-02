package blackjack.domain.model;

import blackjack.domain.vo.Letter;
import blackjack.domain.vo.Shape;
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
        int expectedValue = card1.getValue() + card2.getValue();

        // when
        cards.add(card1);
        cards.add(card2);

        // that
        assertThat(expectedValue).isEqualTo(cards.calculateTotalScore());
    }

    @Test
    @DisplayName("패를 반환한다")
    void getCardsTest(){
        // given
        Cards cards = new Cards();
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        // when
        cards.add(card1);
        cards.add(card2);

        // that
        assertThat(cards.getCards()).contains(card1,card2);
    }

    @Test
    @DisplayName("카드 한장을 반환하는 테스트")
    void getFirstCardTest(){
        // given
        Cards cards = new Cards();
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        // when
        cards.add(card1);
        cards.add(card2);

        // that
        assertThat(cards.getFirstCard().getCardName()).isEqualTo(card1.getCardName());
    }
}

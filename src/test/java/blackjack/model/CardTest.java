package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("카드 한 장 생성 확인")
    void createCardTest() {
        // given
        Figure spade = Figure.SPADE;
        Number five = Number.FIVE;
        // when
        Card card = new Card(spade, five);
        // then
        assertThat(card.getCardName()).isEqualTo("5스페이드");
    }

    @Test
    @DisplayName("문양과 숫자가 같으면 같은 객체이다.")
    void sameObjectTest() {
        // given
        Card card = new Card(Figure.SPADE, Number.FIVE);
        Card card1 = new Card(Figure.SPADE, Number.FIVE);
        // when & then
        assertThat(card).isEqualTo(card1);
    }

    @Test
    @DisplayName("문양이나 숫자가 다르면 다른 객체이다.")
    void differentObjectTest() {
        // give
        Card card = new Card(Figure.SPADE, Number.FIVE);
        Card card1 = new Card(Figure.HEART, Number.FIVE);
        Card card2 = new Card(Figure.SPADE, Number.SIX);
        // when & then
        assertThat(card).isNotEqualTo(card1);
        assertThat(card).isNotEqualTo(card2);
    }

    @Test
    @DisplayName("동일한 카드를 Set에 넣으면 중복이 제거된다.")
    void setTest() {
        // give
        Set<Card> cards = new HashSet<>();
        // when
        cards.add(new Card(Figure.SPADE, Number.FIVE));
        cards.add(new Card(Figure.SPADE, Number.FIVE));
        // then
        assertThat(cards).hasSize(1);
    }
}

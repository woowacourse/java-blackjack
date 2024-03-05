package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("중복된 카드가 포함된 카드 더미를 생성할 수 없다.")
    void validateDuplicate() {
        Card card = new Card(CardNumber.ACE, CardShape.HEART);
        List<Card> cards = List.of(card, card);

        assertThatThrownBy(() -> new Cards(cards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("중복된 카드는 존재할 수 없습니다.");
    }

    @Test
    @DisplayName("카드의 합을 계산한다.")
    void sum() {
        // given
        Card card1 = new Card(CardNumber.TWO, CardShape.HEART);
        Card card2 = new Card(CardNumber.THREE, CardShape.CLOVER);
        Cards cards = new Cards(List.of(card1, card2));

        // when
        int sum = cards.sum();

        // then
        assertThat(sum).isEqualTo(5);
    }

    @Test
    @DisplayName("카드의 에이스 수를 센다.")
    void countAce() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardShape.HEART);
        Card card2 = new Card(CardNumber.ACE, CardShape.SPADE);
        Card card3 = new Card(CardNumber.THREE, CardShape.CLOVER);
        Cards cards = new Cards(List.of(card1, card2, card3));

        // when
        int aceCount = cards.countAce();

        // then
        assertThat(aceCount).isEqualTo(2);
    }

    @Test
    void add() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardShape.HEART);
        Card card2 = new Card(CardNumber.ACE, CardShape.SPADE);
        Cards cards = new Cards(List.of(card1, card2));

        Card card3 = new Card(CardNumber.ACE, CardShape.CLOVER);

        // when
        cards.add(card3);

        // then
        assertThat(cards).extracting("values")
                .isEqualTo(List.of(card1, card2, card3));
    }
}

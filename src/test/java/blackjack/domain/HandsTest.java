package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {

    @DisplayName("중복된 카드가 포함된 카드 더미를 생성할 수 없다.")
    @Test
    void validateDuplicate() {
        Card card = new Card(CardNumber.ACE, CardShape.HEART);
        List<Card> cards = List.of(card, card);

        assertThatThrownBy(() -> new Hands(cards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("중복된 카드는 존재할 수 없습니다.");
    }

    @DisplayName("카드의 합을 계산한다.")
    @Test
    void sum() {
        // given
        Card card1 = new Card(CardNumber.TWO, CardShape.HEART);
        Card card2 = new Card(CardNumber.THREE, CardShape.CLOVER);
        Hands hands = new Hands(List.of(card1, card2));

        // when
        int sum = hands.sum();

        // then
        assertThat(sum).isEqualTo(5);
    }

    @DisplayName("카드의 에이스 수를 센다.")
    @Test
    void countAce() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardShape.HEART);
        Card card2 = new Card(CardNumber.ACE, CardShape.SPADE);
        Card card3 = new Card(CardNumber.THREE, CardShape.CLOVER);
        Hands hands = new Hands(List.of(card1, card2, card3));

        // when
        int aceCount = hands.countAce();

        // then
        assertThat(aceCount).isEqualTo(2);
    }

    @DisplayName("카드를 추가한다.")
    @Test
    void add() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardShape.HEART);
        Card card2 = new Card(CardNumber.ACE, CardShape.SPADE);
        Hands hands = new Hands(List.of(card1, card2));

        Card card3 = new Card(CardNumber.ACE, CardShape.CLOVER);

        // when
        hands.add(card3);

        // then
        assertThat(hands.getCards()).containsExactly(card1, card2, card3);
    }
}

package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CardsTest {
    @DisplayName("카드 합 계산 확인")
    @Test
    void sumTest() {
        Cards cards = new Cards();
        cards.add(Card.of(Type.THREE, Figure.CLOVER));
        cards.add(Card.of(Type.KING, Figure.CLOVER));

        int expected = 13;
        assertThat(cards.computeScore()).isEqualTo(expected);
    }

    @DisplayName("Ace가 있을 때의 카드 합 계산 확인(21이 초과하는 경우)")
    @Test
    void aceSumTest() {
        Cards cards = new Cards();
        cards.add(Card.of(Type.ACE, Figure.CLOVER));
        cards.add(Card.of(Type.KING, Figure.CLOVER));
        cards.add(Card.of(Type.QUEEN, Figure.HEART));

        int expected = 21;
        assertThat(cards.computeScore()).isEqualTo(expected);
    }

    @DisplayName("Ace가 있을 때의 카드 합 계산 확인(21이 초과하지 않는 경우)")
    @Test
    void aceSumTest2() {
        Cards cards = new Cards();
        cards.add(Card.of(Type.ACE, Figure.CLOVER));
        cards.add(Card.of(Type.KING, Figure.CLOVER));

        int expected = 21;
        assertThat(cards.computeScore()).isEqualTo(expected);
    }
}

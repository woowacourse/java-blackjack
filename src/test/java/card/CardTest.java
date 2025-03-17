package card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("CardShape가 같다면 true를 반환한다.")
    void test1() {
        // given
        Card card = new Card(CardShape.SPADE, CardNumber.ACE);

        // when
        boolean result = card.isSameCardShape(CardShape.SPADE);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("CardShape가 다르다면 false를 반환한다.")
    void test2() {
        // given
        Card card = new Card(CardShape.SPADE, CardNumber.ACE);

        // when
        boolean result = card.isSameCardShape(CardShape.HEART);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("CardNumber가 같다면 true를 반환한다.")
    void test3() {
        // given
        Card card = new Card(CardShape.SPADE, CardNumber.ACE);

        // when
        boolean result = card.isSameCardNumber(CardNumber.ACE);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("CardNumber가 다르다면 false를 반환한다.")
    void test4() {
        // given
        Card card = new Card(CardShape.SPADE, CardNumber.ACE);

        // when
        boolean result = card.isSameCardNumber(CardNumber.KING);

        // then
        assertThat(result).isFalse();
    }
}

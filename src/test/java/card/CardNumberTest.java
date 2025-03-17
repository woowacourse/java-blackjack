package card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {

    @Test
    @DisplayName("CardShape가 같다면 true를 반환한다.")
    void test1() {
        // given
        CardNumber cardNumber = CardNumber.ACE;

        // when
        boolean result = cardNumber.isSame(CardNumber.ACE);

        // then
        assertThat(result)
                .isTrue();
    }

    @Test
    @DisplayName("CardShape가 다르다면 false를 반환한다.")
    void test2() {
        // given
        CardNumber cardNumber = CardNumber.ACE;

        // when
        boolean result = cardNumber.isSame(CardNumber.KING);

        // then
        assertThat(result)
                .isFalse();
    }
}

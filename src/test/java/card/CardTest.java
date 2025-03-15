package card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("ACE 카드라면 true를 반환한다.")
    void test1() {
        // given
        Card aceCard = new Card(CardShape.SPADE, CardNumber.ACE);

        // when
        boolean result = aceCard.isAce();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("ACE 카드가 아니라면 false를 반환한다.")
    void test2() {
        // given
        Card aceCard = new Card(CardShape.SPADE, CardNumber.THREE);

        // when
        boolean result = aceCard.isAce();

        // then
        assertThat(result).isFalse();
    }
}

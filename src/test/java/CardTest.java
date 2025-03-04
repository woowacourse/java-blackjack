import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("객체 생성 테스트")
    @Test
    void test1() {
        // given & when
        Card card = new Card(CardType.ACE, 1);

        // then
        assertThat(card.getValue()).isEqualTo(1);
    }

    @DisplayName("값과 타입으로 동일한 객체인지 확인한다")
    @Test
    void test2() {
        // given
        Card card = new Card(CardType.ACE, 1);
        Card comparedCard = new Card(CardType.ACE, 1);

        // when
        boolean actual = card.equals(comparedCard);

        // then
        assertThat(actual).isTrue();
    }
}

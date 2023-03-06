package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardTest {

    @Test
    @DisplayName("카드는 모양과 숫자로 생성할 수 있다.")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new Card(Shape.SPADE, Letter.TWO));
    }

    @Test
    @DisplayName("정확한 카드 숫자와 모양이 출력되는지 테스트")
    void getCardNameTest() {
        // given
        Card card = new Card(Shape.DIAMOND, Letter.ACE);
        String actual = card.getCardName();
        String expected = Letter.ACE.getName() + Shape.DIAMOND.getValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드가 에이스 인지 확인하는 테스트")
    void isAceTest() {
        Card card = new Card(Shape.DIAMOND, Letter.ACE);

        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("카드가 에이스 아닌지 확인하는 테스트")
    void isNotAceTest() {
        Card card = new Card(Shape.DIAMOND, Letter.EIGHT);

        assertThat(card.isAce()).isFalse();
    }
}

package blackjack.domain.model;

import blackjack.domain.vo.Letter;
import blackjack.domain.vo.Name;
import blackjack.domain.vo.Shape;
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
        Player player = new Player(new Name("test"), new Cards());
        String expected = card.getCardName();

        // when
        player.drawCard(card);
        String actual = player.getOneCard().get(0);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
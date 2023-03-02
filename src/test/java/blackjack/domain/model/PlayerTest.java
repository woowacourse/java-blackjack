package blackjack.domain.model;

import blackjack.domain.vo.Letter;
import blackjack.domain.vo.Name;
import blackjack.domain.vo.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class PlayerTest {
    @Test
    @DisplayName("플레이어 생성 테스트")
    void constructorPlayer(){
        assertThatNoException().isThrownBy(()->new Player(new Name("test"),new Cards()));
    }

    @Test
    @DisplayName("카드 한장을 가져오는지 테스트")
    void drawCardTest() {
        // given
        Player player = new Player(new Name("test"), new Cards());
        Card card = new Card(Shape.CLOVER, Letter.ACE);

        // when
        player.drawCard(card);

        // then
        Assertions.assertThat(player.getCards())
                .contains(card);
    }

}

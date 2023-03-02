package blackjack.domain.model;

import blackjack.domain.vo.Letter;
import blackjack.domain.vo.Name;
import blackjack.domain.vo.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @Test
    @DisplayName("카드의 총 합을 보여주는 테스트")
    void calculateTotal(){
        // given
        Player player = new Player(new Name("test"), new Cards());
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        int expected = card1.getValue()+card2.getValue();
        player.drawCard(card1);
        player.drawCard(card2);

        // when
        int actual = player.calculateTotal();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}

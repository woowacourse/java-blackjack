package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("player 이름이 5자를 초과하면 예외가 발생한다 ")
    void nameValidateTest() {

        String name = "abcdef";
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Number.FIVE), new Card(Suit.CLOVER, Number.SIX)));

        Assertions.assertThatThrownBy(() -> new Player(name, cards))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 5자 이하여야 합니다.");
    }

    @Test
    @DisplayName("player의 카드 점수가 21 미만이면 계속 카드를 받을 수 있다.")
    void canContinueTrue() {
        //given
        String name = "hoy";
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Number.FIVE), new Card(Suit.CLOVER, Number.SIX)));
        //when
        Player player = new Player(name, cards);
        //then
        assertThat(player.canAddCard()).isTrue();
    }

    @Test
    @DisplayName("player의 카드 점수가 21 이상이면 계속 카드를 받을 수 없다.")
    void canContinueFalse() {
        //given
        String name = "hoy";
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Number.J), new Card(Suit.CLOVER, Number.A)));
        //when
        Player player = new Player(name, cards);
        //then
        assertThat(player.canAddCard()).isFalse();
    }
}

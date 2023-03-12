package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("player 이름이 5자를 초과하면 예외가 발생한다 ")
    void nameValidateTest() {

        String name = "abcdef";
        Assertions.assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 5자 이하여야 합니다.");
    }
    
    @Test
    @DisplayName("player는 카드를 받기전에 뽑을 수 없다.")
    void validateHit() {
        Player hoy = new Player("hoy");

        Assertions.assertThatThrownBy(() -> hoy.hit(new Card(Suit.SPADE, Denomination.A)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("카드를 먼저 받으세요.");
    }

    @Test
    @DisplayName("player의 카드 점수가 21 미만이면 계속 카드를 받을 수 있다.")
    void canContinueTrue() {
        //given
        String name = "hoy";
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Denomination.FIVE), new Card(Suit.CLOVER, Denomination.SIX)));
        //when
        Player player = new Player(name);
        player.initCards(cards);
        //then
        assertThat(player.canAddCard()).isTrue();
    }

    @Test
    @DisplayName("player의 카드 점수가 21 이상이면 계속 카드를 받을 수 없다.")
    void canContinueFalse() {
        //given
        String name = "hoy";
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Denomination.J), new Card(Suit.CLOVER, Denomination.A)));
        //when
        Player player = new Player(name);
        player.initCards(cards);
        //then
        assertThat(player.canAddCard()).isFalse();
    }

    @Test
    @DisplayName("딜러와 같은 이름을 사용하면 예외가 발생한다.")
    void validateSameDealerName() {
        Assertions.assertThatThrownBy(() -> new Player(Dealer.NAME))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("딜러와 같은 이름");
    }
}

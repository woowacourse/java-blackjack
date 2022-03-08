package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class PlayerTest {
    private Player player;

    @BeforeEach
    void makePlayer(){
        player = new Player("test");
    }

    @Test
    @DisplayName("이름이 공백인 경우 예외를 발생한다.")
    void playerEmptyNameTest() {
        String name = "";

        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("제대로 카드를 뽑는지 확인.")
    void hitCardTest() {
        Card card = new Card(Symbol.SPADE, Denomination.ACE);
        player.hit(card);

        assertThat(player.getCards().get(0)).isEqualTo(card);
    }

    @Test
    @DisplayName("21이 넘은 상태에서 카드를 뽑을 경우 에러를 발생시킨다.")
    void hitCardOverLimitError() {
        player.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        player.hit(new Card(Symbol.SPADE, Denomination.NINE));
        player.hit(new Card(Symbol.SPADE, Denomination.TEN));

        assertThatThrownBy(() ->  player.hit(new Card(Symbol.SPADE, Denomination.ACE)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
    }

    @Test
    @DisplayName("숫자가 21이 넘는 경우 False를 반환한다.")
    void canDrawCardFalseTest() {
        player.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        player.hit(new Card(Symbol.SPADE, Denomination.NINE));
        player.hit(new Card(Symbol.SPADE, Denomination.TEN));

        assertThat(player.canDrawCard()).isFalse();
    }

    @Test
    @DisplayName("숫자가 21이 넘지 않는 경우 True를 반환한다.")
    void canDrawCardTrueTest() {
        player.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        player.hit(new Card(Symbol.SPADE, Denomination.NINE));

        assertThat(player.canDrawCard()).isTrue();
    }
}
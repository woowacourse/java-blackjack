package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Symbol;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class PlayerTest {

    private Player player;

    @BeforeEach
    void init() {
        player = new Player("test", 10000);
    }

    @Test
    @DisplayName("이름이 공백인 경우 예외를 발생한다.")
    void playerEmptyNameTest() {
        String name = "";

        assertThatThrownBy(() -> new Player(name, 10))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("제대로 카드를 뽑는지 확인.")
    void hitCardTest() {
        Card card = Card.of(Symbol.SPADE, Denomination.ACE);
        player.hit(card);

        assertThat(player.getCards().get(0)).isEqualTo(card);
    }

    @Test
    @DisplayName("21이 넘은 상태에서 카드를 뽑을 경우 에러를 발생시킨다.")
    void hitCardOverLimitError() {
        player.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));
        player.hit(Card.of(Symbol.SPADE, Denomination.NINE));
        player.hit(Card.of(Symbol.SPADE, Denomination.TEN));

        assertThatThrownBy(() -> player.hit(Card.of(Symbol.SPADE, Denomination.ACE)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
    }

    @Test
    @DisplayName("숫자가 21이 넘는 경우 False를 반환한다.")
    void canDrawCardFalseTest() {
        player.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));
        player.hit(Card.of(Symbol.SPADE, Denomination.NINE));
        player.hit(Card.of(Symbol.SPADE, Denomination.TEN));

        assertThat(player.canDrawCard()).isFalse();
    }

    @Test
    @DisplayName("숫자가 21이 넘지 않는 경우 True를 반환한다.")
    void canDrawCardTrueTest() {
        player.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));
        player.hit(Card.of(Symbol.SPADE, Denomination.NINE));

        assertThat(player.canDrawCard()).isTrue();
    }

    @Test
    @DisplayName("결과가 블랙잭인 경우 계산이 제대로 되는지 확인한다.")
    void multiplyTest_BlackJack() {
        assertThat(player.multiply(PlayerResult.BLACKJACK)).isEqualTo(BigDecimal.valueOf(15000.0));
    }

    @Test
    @DisplayName("결과가 동점인 경우 계산이 제대로 되는지 확인한다.")
    void multiplyTest_Draw() {
        assertThat(player.multiply(PlayerResult.DRAW)).isEqualTo(BigDecimal.valueOf(0.0));
    }

    @Test
    @DisplayName("결과가 승리인 경우 계산이 제대로 되는지 확인한다.")
    void multiplyTest_Win() {
        assertThat(player.multiply(PlayerResult.WIN)).isEqualTo(BigDecimal.valueOf(10000.0));
    }

    @Test
    @DisplayName("결과가 패배인 경우 계산이 제대로 되는지 확인한다.")
    void multiplyTest_Lose() {
        assertThat(player.multiply(PlayerResult.LOSE)).isEqualTo(BigDecimal.valueOf(-10000.0));
    }
}
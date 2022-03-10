package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class PlayerTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void init() {
        player = new Player("test");
        dealer = new Dealer();
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

        assertThatThrownBy(() -> player.hit(new Card(Symbol.SPADE, Denomination.ACE)))
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

    @Test
    @DisplayName("Player가 21이 넘고, 딜러가 21이 넘지 않을 경우 Lose를 반환한다.")
    void isWinTest() {
        player.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        player.hit(new Card(Symbol.SPADE, Denomination.NINE));
        player.hit(new Card(Symbol.HEART, Denomination.NINE));

        dealer.hit(new Card(Symbol.SPADE, Denomination.SEVEN));

        assertThat(player.isWin(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("Player가 21이 넘고, 딜러도 21을 경우 Win을 반환한다.")
    void isWinTest2() {
        player.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        player.hit(new Card(Symbol.SPADE, Denomination.NINE));
        player.hit(new Card(Symbol.HEART, Denomination.NINE));

        dealer.hit(new Card(Symbol.SPADE, Denomination.SEVEN));
        dealer.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        dealer.hit(new Card(Symbol.SPADE, Denomination.QUEEN));

        assertThat(player.isWin(dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("Player와 딜러 모두 21을 넘지 않고, Player가 총 점수가 클 경우 Win을 반환한다.")
    void isWinTest3() {
        player.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        player.hit(new Card(Symbol.SPADE, Denomination.NINE));

        dealer.hit(new Card(Symbol.SPADE, Denomination.SEVEN));
        dealer.hit(new Card(Symbol.SPADE, Denomination.EIGHT));

        assertThat(player.isWin(dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("Player와 딜러 모두 21을 넘지 않고, Dealer가 총 점수가 클 경우 Lose를 반환한다.")
    void isWinTest4() {
        player.hit(new Card(Symbol.SPADE, Denomination.EIGHT));
        player.hit(new Card(Symbol.SPADE, Denomination.FIVE));

        dealer.hit(new Card(Symbol.SPADE, Denomination.SEVEN));
        dealer.hit(new Card(Symbol.SPADE, Denomination.EIGHT));

        assertThat(player.isWin(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("Player와 딜러 모두 21을 넘지 않고, 점수가 같을 경우 Draw를 반환한다.")
    void isWinTest5() {
        player.hit(new Card(Symbol.SPADE, Denomination.FIVE));
        player.hit(new Card(Symbol.SPADE, Denomination.FIVE));

        dealer.hit(new Card(Symbol.SPADE, Denomination.SIX));
        dealer.hit(new Card(Symbol.SPADE, Denomination.FOUR));

        assertThat(player.isWin(dealer)).isEqualTo(Result.DRAW);
    }
}
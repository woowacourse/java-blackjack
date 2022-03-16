package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class PlayerTest {

    private Player player;
    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void init() {
        player = new Player("test");
        dealer = new Dealer();
        deck = Deck.initDeck();
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
    @DisplayName("21이 넘은 상태에서 카드를 뽑을 경우 에러를 발생시킨다.")
    void hitCardOverLimitError() {
        while (player.canHit()) {
            player.hit(deck);
        }

        assertThatThrownBy(() -> player.hit(deck))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
    }

    @Test
    @DisplayName("숫자가 21이 넘는 경우 False를 반환한다.")
    void canDrawCardFalseTest() {
        while (player.canHit()) {
            player.hit(deck);
        }

        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("숫자가 21이 넘지 않는 경우 True를 반환한다.")
    void canDrawCardTrueTest() {
        player.hit(deck);

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("Player가 21이 넘고, 딜러가 21이 넘지 않을 경우 Lose를 반환한다.")
    void isWinTest() {
        while (player.canHit()) {
            player.hit(deck);
        }

        dealer.hit(deck);

        assertThat(player.judgeResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("Player가 21을 경우, Dealer와 상관 없이 Lose를 반환한다.")
    void isWinTest2() {
        while (player.canHit()) {
            player.hit(deck);
        }

        assertThat(player.judgeResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("Player와 딜러 모두 21을 넘지 않고, Player가 총 점수가 클 경우 Win을 반환한다.")
    void isWinTest3() {
        player.hit(deck);

        assertThat(player.judgeResult(dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("Player와 딜러 모두 21을 넘지 않고, Dealer가 총 점수가 클 경우 Lose를 반환한다.")
    void isWinTest4() {
        dealer.hit(deck);

        assertThat(player.judgeResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("Player와 딜러 모두 21을 넘지 않고, 점수가 같을 경우 Draw를 반환한다.")
    void isWinTest5() {
        assertThat(player.judgeResult(dealer)).isEqualTo(Result.DRAW);
    }
}
package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("hit이 가능하다면 참을 반환한다")
    void canHitWhenTrue() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.HEART, Denomination.KING));

        assertThat(player.isAbleToHit()).isTrue();
    }

    @Test
    @DisplayName("hit이 불가능하다면 거짓을 반환한다")
    void canHitWhenFalse() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.HEART, Denomination.KING));
        player.addCard(new Card(Symbol.CLOVER, Denomination.QUEEN));
        player.addCard(new Card(Symbol.SPADE, Denomination.ACE));

        assertThat(player.isAbleToHit()).isFalse();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 총점이 21 이하인 경우")
    void testWinOrLose() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.SPADE, Denomination.FIVE));
        player.addCard(new Card(Symbol.DIAMOND, Denomination.TWO));

        assertThat(player.computeResult(16).isWin()).isFalse();
        assertThat(player.computeResult(6).isWin()).isTrue();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 플레이어의 총점이 21 초과인 경우")
    void testWinOrLose2() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.SPADE, Denomination.KING));
        player.addCard(new Card(Symbol.SPADE, Denomination.QUEEN));
        player.addCard(new Card(Symbol.DIAMOND, Denomination.TWO));

        assertThat(player.computeResult(1).isWin()).isFalse();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 딜러의 총점이 21 초과인 경우")
    void testWinOrLose3() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.SPADE, Denomination.ACE));
        player.addCard(new Card(Symbol.SPADE, Denomination.ACE));

        assertThat(player.computeResult(22).isWin()).isTrue();
    }
}

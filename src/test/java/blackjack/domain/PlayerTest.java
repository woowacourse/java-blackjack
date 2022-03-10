package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("bust라면 참을 반환한다")
    void isBustWhenTrue() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.HEART, Denomination.KING));
        player.addCard(new Card(Symbol.CLOVER, Denomination.QUEEN));
        player.addCard(new Card(Symbol.SPADE, Denomination.ACE));

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("bust가 아니라면 거짓을 반환한다")
    void isBustWhenFalse() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.HEART, Denomination.KING));
        player.addCard(new Card(Symbol.CLOVER, Denomination.QUEEN));

        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 총점이 21 이하인 경우")
    void testWinOrLose() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.SPADE, Denomination.FIVE));
        player.addCard(new Card(Symbol.DIAMOND, Denomination.TWO));

        assertThat(player.isWin(16)).isFalse();
        assertThat(player.isWin(6)).isTrue();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 플레이어의 총점이 21 초과인 경우")
    void testWinOrLose2() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.SPADE, Denomination.KING));
        player.addCard(new Card(Symbol.SPADE, Denomination.QUEEN));
        player.addCard(new Card(Symbol.DIAMOND, Denomination.TWO));

        assertThat(player.isWin(1)).isFalse();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 딜러의 총점이 21 초과인 경우")
    void testWinOrLose3() {
        Player player = new Player("pobi");
        player.addCard(new Card(Symbol.SPADE, Denomination.ACE));
        player.addCard(new Card(Symbol.SPADE, Denomination.ACE));

        assertThat(player.isWin(22)).isTrue();
    }
}

package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("hit 가능하다면 참을 반환한다")
    void canHitWhenTrue() {
        Player player = new Player("pobi", List.of(
                new Card(Symbol.HEART, Denomination.KING),
                new Card(Symbol.CLOVER, Denomination.QUEEN)
        ));

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("hit 가능하지않다면 거짓을 반환한다")
    void canHitWhenFalse() {
        Player player = new Player("pobi", List.of(
                new Card(Symbol.HEART, Denomination.KING),
                new Card(Symbol.CLOVER, Denomination.QUEEN)
        ));

        player.addCard(new Card(Symbol.SPADE, Denomination.ACE));

        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 총점이 21 이하인 경우")
    void testWinOrLose() {
        Player player = new Player("pobi",List.of(
                new Card(Symbol.SPADE,Denomination.FIVE),
                new Card(Symbol.DIAMOND,Denomination.TWO)
        ));
        assertThat(player.isWin(16)).isFalse();
        assertThat(player.isWin(6)).isTrue();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 플레이어의 총점이 21 초과인 경우")
    void testWinOrLose2() {
        Player player = new Player("pobi",List.of(
                new Card(Symbol.SPADE,Denomination.KING),
                new Card(Symbol.SPADE,Denomination.QUEEN),
                new Card(Symbol.DIAMOND,Denomination.TWO)
        ));
        assertThat(player.isWin(1)).isFalse();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 딜러의 총점이 21 초과인 경우")
    void testWinOrLose3() {
        Player player = new Player("pobi",List.of(
                new Card(Symbol.SPADE,Denomination.ACE),
                new Card(Symbol.SPADE,Denomination.ACE)
        ));
        assertThat(player.isWin(22)).isTrue();
    }
}

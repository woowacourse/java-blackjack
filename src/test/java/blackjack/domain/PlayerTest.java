package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("hit 가능하다면 참을 반환한다")
    void canHitWhenTrue() {
        Player player = new Player(List.of(
                new Card(Symbol.HEART, Denomination.KING),
                new Card(Symbol.CLOVER, Denomination.QUEEN)
        ));

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("hit 가능하지않다면 거짓을 반환한다")
    void canHitWhenFalse() {
        Player player = new Player(List.of(
                new Card(Symbol.HEART, Denomination.KING),
                new Card(Symbol.CLOVER, Denomination.QUEEN)
        ));

        player.addCard(new Card(Symbol.SPADE, Denomination.ACE));

        assertThat(player.canHit()).isFalse();
    }
}

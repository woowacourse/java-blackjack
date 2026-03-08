package domain;

import org.junit.jupiter.api.Test;

import static domain.constant.Rank.*;
import static domain.constant.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void 플레이어는_이름과_핸드를_가진다() {
        Player player = new Player("pobi", new Hand());

        assertThat(player.getName()).isEqualTo("pobi");
        assertThat(player.showHand()).isEmpty();
    }

    @Test
    void 플레이어_기능_테스트() {
        Player player = new Player("pobi", new Hand());

        player.addCard(new Card(ACE, SPADE));
        player.addCard(new Card(KING, SPADE));

        assertAll(
                () -> assertFalse(player.isBust()),
                () -> assertFalse(player.canReceiveCard()),
                () -> assertEquals(21, player.getScore())
        );
    }
}
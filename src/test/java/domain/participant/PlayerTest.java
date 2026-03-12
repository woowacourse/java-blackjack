package domain.participant;

import domain.Bet;
import domain.Card;
import domain.Hand;
import org.junit.jupiter.api.Test;

import static domain.constant.Rank.*;
import static domain.constant.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void 플레이어는_이름과_핸드를_가진다() {
        Player player = new Player("pobi", new Hand(), "10000");

        assertThat(player.getName()).isEqualTo("pobi");
        assertThat(player.showHand()).isEmpty();
    }

    @Test
    void 플레이어_기능_테스트() {
        Player player = new Player("pobi", new Hand(), "10000");

        player.receiveCard(new Card(ACE, SPADE));
        player.receiveCard(new Card(KING, SPADE));

        assertAll(
                () -> assertFalse(player.isBust()),
                () -> assertFalse(player.canReceiveCard()),
                () -> assertEquals(21, player.getScore())
        );
    }

    @Test
    void 합계_점수에_따라_카드를_더_받을_수_있는지_판정한다() {
        Player player = new Player("pobi", new Hand(), "10000");
        player.receiveCard(new Card(TWO, SPADE));
        player.receiveCard(new Card(NINE, SPADE));


        assertThat(player.canReceiveCard()).isTrue();

        player.receiveCard(new Card(TEN, HEART));

        assertThat(player.canReceiveCard()).isFalse();
    }
}
package blackjack.domain;

import org.junit.jupiter.api.Test;

import static blackjack.domain.CardNumber.KING;
import static blackjack.domain.CardShape.CLOVER;
import static blackjack.domain.CardShape.HEART;
import static blackjack.domain.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void 플레이어는_죽었는지_여부를_반환한다() {
        Player player = new Player(new Hand());
        player.addCards(new Card(KING, CLOVER), new Card(KING, SPADE), new Card(KING, HEART));

        boolean isDead = player.isDead();

        assertThat(isDead).isTrue();
    }
}

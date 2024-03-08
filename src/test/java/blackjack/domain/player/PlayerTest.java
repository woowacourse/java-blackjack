package blackjack.domain.player;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardShape.CLOVER;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardShape.SPADE;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void 플레이어는_죽었는지_여부를_반환한다() {
        Player player = player(
                new Card(KING, CLOVER),
                new Card(KING, SPADE),
                new Card(KING, HEART));

        boolean isAlive = player.isAlive();

        assertThat(isAlive).isFalse();
    }
}

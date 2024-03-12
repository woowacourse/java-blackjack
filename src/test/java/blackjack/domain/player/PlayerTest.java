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
    void 플레이어가_BUST_되었는지_여부를_반환한다() {
        // given
        Player player = player(
                new Card(KING, CLOVER),
                new Card(KING, SPADE),
                new Card(KING, HEART));

        // when
        boolean isBust = player.isBust();

        // then
        assertThat(isBust).isFalse();
    }
}

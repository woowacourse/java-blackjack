package blackjack.domain.player;

import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 플레이어가_BUST_되었는지_여부를_반환한다() {
        // given
        Player player = player(0,
                new Card(KING, CLOVER),
                new Card(KING, SPADE),
                new Card(KING, HEART));

        // when
        boolean isBust = player.isBust();

        // then
        assertThat(isBust).isTrue();
    }
}

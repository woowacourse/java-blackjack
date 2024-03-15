package blackjack.domain.cardgame;

import blackjack.domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.WIN;
import static blackjack.fixture.PlayerFixture.player;

class CardGameResultTest {
    @Test
    void 플레이어가_2승이면_딜러는_2패다() {
        // given
        Map<Player, WinningStatus> result = new LinkedHashMap<>();
        result.put(player(), WIN);
        result.put(player(), WIN);

        CardGameResult cardGameResult = new CardGameResult(result);

        // when
        int dealerLoseCount = cardGameResult.getDealerLoseCount();

        // then
        Assertions.assertThat(dealerLoseCount).isEqualTo(2);
    }

    @Test
    void 플레이어가_1패면_딜러는_1승이다() {
        // given
        Map<Player, WinningStatus> result = new LinkedHashMap<>();
        result.put(player(), LOSE);

        CardGameResult cardGameResult = new CardGameResult(result);

        // when
        int dealerWinCount = cardGameResult.getDealerWinCount();

        // then
        Assertions.assertThat(dealerWinCount).isEqualTo(1);
    }
}

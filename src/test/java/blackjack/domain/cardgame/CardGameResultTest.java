package blackjack.domain.cardgame;

import blackjack.domain.player.Player;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.WIN;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;

class CardGameResultTest {
    @Test
    void 딜러가_승패_횟수를_계산할_수_있다() {
        Map<Player, WinningStatus> result = new LinkedHashMap<>();
        result.put(player(), WIN);
        result.put(player(), WIN);
        result.put(player(), LOSE);

        CardGameResult cardGameResult = new CardGameResult(result);

        int dealerWinCount = cardGameResult.getDealerWinCount();
        int dealerLoseCount = cardGameResult.getDealerLoseCount();

        assertThat(dealerWinCount).isEqualTo(1);
        assertThat(dealerLoseCount).isEqualTo(2);
    }
}

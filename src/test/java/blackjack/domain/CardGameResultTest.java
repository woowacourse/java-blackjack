package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static blackjack.domain.WinningStatus.LOSE;
import static blackjack.domain.WinningStatus.WIN;
import static blackjack.fixture.PlayerFixture.player;

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

        Assertions.assertThat(dealerWinCount).isEqualTo(1);
        Assertions.assertThat(dealerLoseCount).isEqualTo(2);
    }
}

package blackjack.domain.game;

import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Result;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BlackjackGameResultTest {

    @Test
    void 게임_결과를_반환한다() {
        final BlackjackGameResult blackjackGameResult = generateBlackjackGameResult(Result.WIN);

        assertThat(blackjackGameResult.getResult().values()).containsExactly(Result.WIN, Result.WIN);
    }

    private BlackjackGameResult generateBlackjackGameResult(final Result result) {
        final LinkedHashMap<Player, Result> map = new LinkedHashMap<>();
        map.put(new Gambler("허브"), result);
        map.put(new Gambler("후추"), result);

        return new BlackjackGameResult(map);
    }

    @Test
    void 딜러_승리_수를_반환한다() {
        final BlackjackGameResult blackjackGameResult = generateBlackjackGameResult(Result.LOSE);

        assertThat(blackjackGameResult.calculateDealerWinCount()).isEqualTo(2);
    }

    @Test
    void 딜러_무승부_수를_반환한다() {
        final BlackjackGameResult blackjackGameResult = generateBlackjackGameResult(Result.PUSH);

        assertThat(blackjackGameResult.calculateDealerDrawCount()).isEqualTo(2);
    }

    @Test
    void 딜러_패배_수를_반환한다() {
        final BlackjackGameResult blackjackGameResult = generateBlackjackGameResult(Result.WIN);

        assertThat(blackjackGameResult.calculateDealerLoseCount()).isEqualTo(2);
    }
}

package bet;

import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import player.Participant;
import player.Player;
import result.MatchResult;

class BetManagerTest {

    @Test
    void 딜러의_최종_수익_금액을_반환한다() {
        // given
        final int amount = 10000;
        final int expected = -10000;

        Player player = new Participant("시소");
        Map<Player, MatchResult> matchResults = new HashMap<>();
        matchResults.put(player, MatchResult.WIN);

        BetManager betManager = new BetManager();
        betManager.addInitialBet(player, amount);
        betManager.calculateParticipantBetResults(matchResults);

        // when & then
        Assertions.assertThat(betManager.calculateDealerBetResultAmount())
                .isEqualTo(expected);
    }

    @Test
    void 참여자들의_최종_수익_결과를_계산한다() {
        // given
        final int amount = 10000;
        final int expected1 = 10000;
        final int expected2 = 0;

        Player player1 = new Participant("시시");
        Player player2 = new Participant("소소");

        BetManager betManager = new BetManager();
        betManager.addInitialBet(player1, amount);
        betManager.addInitialBet(player2, amount);

        Map<Player, MatchResult> matchResults = new HashMap<>();
        matchResults.put(player1, MatchResult.WIN);
        matchResults.put(player2, MatchResult.DRAW);

        // when
        betManager.calculateParticipantBetResults(matchResults);
        Map<Player, BetResult> betResults = betManager.getBetResults();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(betResults.get(player1).getAmount()).isEqualTo(expected1);
            softly.assertThat(betResults.get(player2).getAmount()).isEqualTo(expected2);
        });
    }
}

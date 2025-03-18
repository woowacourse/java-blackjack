package bet;

import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import player.Participant;
import match.MatchResult;

class BetManagerTest {

    @Test
    void 딜러의_최종_수익_금액을_반환한다() {
        // given
        final int amount = 10000;
        final int expected = -10000;

        Participant participant = new Participant("시소");
        Map<Participant, MatchResult> matchResults = new HashMap<>();
        matchResults.put(participant, MatchResult.WIN);

        BetManager betManager = new BetManager();
        betManager.addInitialBet(participant, amount);
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

        Participant participant1 = new Participant("시시");
        Participant participant2 = new Participant("소소");

        BetManager betManager = new BetManager();
        betManager.addInitialBet(participant1, amount);
        betManager.addInitialBet(participant2, amount);

        Map<Participant, MatchResult> matchResults = new HashMap<>();
        matchResults.put(participant1, MatchResult.WIN);
        matchResults.put(participant2, MatchResult.DRAW);

        // when
        betManager.calculateParticipantBetResults(matchResults);
        Map<Participant, BetResult> betResults = betManager.getBetResults();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(betResults.get(participant1).getAmount()).isEqualTo(expected1);
            softly.assertThat(betResults.get(participant2).getAmount()).isEqualTo(expected2);
        });
    }
}

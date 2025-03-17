package bet;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import player.Participant;
import result.MatchResult;

class BetResultsTest {

    @Test
    void 딜러의_최종_수익_금액을_계산한다() {
        // given
        final int amount = 10000;
        final int expected = -10000;

        Participant participant = new Participant("시소");
        BetResult betResult = new BetResult(MatchResult.WIN, amount);

        BetResults betResults = new BetResults();
        betResults.addBetResult(participant, betResult);

        // when & then
        Assertions.assertThat(betResults.calculateDealerBettingResult())
                .isEqualTo(expected);
    }

    @Test
    void 참여자들의_최종_수익_금액을_계산한다() {
        // given
        final int amount = 10000;
        Participant participant1 = new Participant("시시");
        Participant participant2 = new Participant("소소");

        BetResults betResults = new BetResults();
        BetResult betResult1 = new BetResult(MatchResult.WIN, amount);
        BetResult betResult2 = new BetResult(MatchResult.DRAW, amount);

        // when
        betResults.addBetResult(participant1, betResult1);
        betResults.addBetResult(participant2, betResult2);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(betResults.getBetResults().get(participant1).getAmount()).isEqualTo(amount);
            softly.assertThat(betResults.getBetResults().get(participant2).getAmount()).isEqualTo(0);
        });
    }
}

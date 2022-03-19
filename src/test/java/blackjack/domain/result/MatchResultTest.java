package blackjack.domain.result;

import static blackjack.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Player;

class MatchResultTest {

    @DisplayName("블랙잭으로 승리한 경우, 베팅 금액의 1.5배의 수익을 받는다.")
    @Test
    void calculatePlayerOutcomeAboutBlackjackTest() {
        final Player player = Player.readyToPlay("name", List.of(SPADE_ACE, SPADE_TEN));
        player.betAmount(1000);

        final MatchResult matchResult = new MatchResult(Map.of(player, MatchStatus.BLACKJACK));
        final int actualOutcome = matchResult.getPlayerOutcomes().get("name");
        final int expectedOutcome = 1500;
        assertThat(actualOutcome).isEqualTo(expectedOutcome);
    }

    @DisplayName("승리한 경우, 베팅 금액의 1배의 수익을 받는다.")
    @Test
    void calculatePlayerOutcomeAboutWinTest() {
        final Player player = Player.readyToPlay("name", List.of(SPADE_ACE, SPADE_TEN));
        player.betAmount(1000);

        final MatchResult matchResult = new MatchResult(Map.of(player, MatchStatus.WIN));
        final int actualOutcome = matchResult.getPlayerOutcomes().get("name");
        final int expectedOutcome = 1000;
        assertThat(actualOutcome).isEqualTo(expectedOutcome);
    }

    @DisplayName("무승부인 경우, 베팅 금액을 그대로 돌려받는다.")
    @Test
    void calculatePlayerOutcomeAboutDrawTest() {
        final Player player = Player.readyToPlay("name", List.of(SPADE_ACE, SPADE_TEN));
        player.betAmount(1000);

        final MatchResult matchResult = new MatchResult(Map.of(player, MatchStatus.DRAW));
        final int actualOutcome = matchResult.getPlayerOutcomes().get("name");
        final int expectedOutcome = 0;
        assertThat(actualOutcome).isEqualTo(expectedOutcome);
    }

    @DisplayName("패배한 경우, 베팅 금액을 잃는다.")
    @Test
    void calculatePlayerOutcomeAboutLOSSTest() {
        final Player player = Player.readyToPlay("name", List.of(SPADE_ACE, SPADE_TEN));
        player.betAmount(1000);

        final MatchResult matchResult = new MatchResult(Map.of(player, MatchStatus.LOSS));
        final int actualOutcome = matchResult.getPlayerOutcomes().get("name");
        final int expectedOutcome = -1000;
        assertThat(actualOutcome).isEqualTo(expectedOutcome);
    }

}
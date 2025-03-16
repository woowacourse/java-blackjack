package blackjack.domain.game;

import blackjack.domain.result.BetAmount;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.Judge;
import blackjack.domain.result.ParticipantResult;
import blackjack.domain.result.ParticipantResults;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class JudeTest {
    @Test
    void 참가자들의_게임_결과를_저장한다() {
        // given
        ParticipantResults participantResults = new ParticipantResults();
        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        Dealer dealer = new Dealer(new Hand());
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        Players players = new Players(List.of(player));
        Judge judge = new Judge(participantResults);

        // when
        judge.calculateAllResults(dealer, players, gameRuleEvaluator);

        // then
        ParticipantResult result = participantResults.findResult(dealer);
        assertThat(result.getCountsOfResultTypes().getOrDefault(GameResultType.TIE, 0)).isEqualTo(1);
    }
}

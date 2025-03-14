package blackjack.domain.game;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.GameRuleEvaluator;
import blackjack.domain.game.Hand;
import blackjack.domain.result.Judge;
import blackjack.domain.game.Participants;
import blackjack.domain.game.Player;
import blackjack.domain.result.GameResultType;
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
        Player player = new Player("히로", new Hand());
        Dealer dealer = new Dealer(new Hand());
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        Participants participants = new Participants(List.of(player, dealer));
        Judge judge = new Judge(participantResults);

        // when
        judge.calculateAllResults(participants, gameRuleEvaluator);

        // then
        ParticipantResult result = participantResults.findResult(dealer);
        assertThat(result.getCountsOfResultTypes().getOrDefault(GameResultType.TIE, 0)).isEqualTo(1);
    }
}

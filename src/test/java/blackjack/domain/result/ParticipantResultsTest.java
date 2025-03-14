package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;

class ParticipantResultsTest {

    @Test
    void 결과를_추가할_수_있다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        ParticipantResult participantResult = new ParticipantResult(dealer, GameResultType.LOSE, 19);
        ParticipantResults participantResults = new ParticipantResults();

        // when
        participantResults.add(participantResult);

        // then
        assertThatCode(() -> participantResults.findResult(dealer)).doesNotThrowAnyException();
    }

    @Test
    void 참가자의_결과를_조회할_수_있다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        ParticipantResult participantResult = new ParticipantResult(dealer, GameResultType.LOSE, 19);
        ParticipantResults participantResults = new ParticipantResults();
        participantResults.add(participantResult);

        // when
        ParticipantResult actual = participantResults.findResult(dealer);

        // then
        assertThat(actual).isEqualTo(participantResult);
    }

    @Test
    void 챌린저의_결과만_조회할_수_있다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        Player player = new Player("히로", new Hand());

        ParticipantResults participantResults = new ParticipantResults();

        ParticipantResult participantResultOfDealer = new ParticipantResult(dealer, GameResultType.LOSE, 19);
        participantResults.add(participantResultOfDealer);

        ParticipantResult participantResultOfPlayer = new ParticipantResult(player, GameResultType.WIN, 19);
        participantResults.add(participantResultOfPlayer);

        // when
        List<ParticipantResult> resultsOfChallenger = participantResults.findResultsOfChallenger();

        // then
        assertThat(resultsOfChallenger).containsExactly(participantResultOfPlayer);
    }

    @Test
    void 디펜더의_결과만_조회할_수_있다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        Player player = new Player("히로", new Hand());

        ParticipantResults participantResults = new ParticipantResults();

        ParticipantResult participantResultOfDealer = new ParticipantResult(dealer, GameResultType.LOSE, 19);
        participantResults.add(participantResultOfDealer);

        ParticipantResult participantResultOfPlayer = new ParticipantResult(player, GameResultType.WIN, 19);
        participantResults.add(participantResultOfPlayer);

        // when
        List<ParticipantResult> resultsOfDefender = participantResults.findResultsOfDefender();

        // then
        assertThat(resultsOfDefender).isEqualTo(List.of(participantResultOfDealer));
    }
}

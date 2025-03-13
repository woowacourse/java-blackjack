package blackjack.domain.result;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;

class ParticipantResultsTest {

    @Test
    void 결과를_추가한다() {
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
    void 참가자의_결과를_조회한다() {
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
}

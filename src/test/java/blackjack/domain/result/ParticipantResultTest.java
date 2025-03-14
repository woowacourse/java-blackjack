package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ParticipantResultTest {

    @Test
    void 같은_참가자에_대한_결과는_동일한_객체로_취급한다() {
        // given
        Player player = new Player("히로", new Hand());

        ParticipantResult participantResult = new ParticipantResult(player, GameResultType.WIN, 21);
        ParticipantResult anotherResult = new ParticipantResult(player, GameResultType.LOSE, 21);

        // when
        boolean actual = participantResult.equals(anotherResult);

        // then
        assertTrue(actual);
    }

    @Test
    void 참가자의_결과를_업데이트할_수_있다() {
        // given
        Dealer dealer = new Dealer(new Hand());

        ParticipantResult participantResult = new ParticipantResult(dealer, GameResultType.LOSE, 21);
        ParticipantResult anotherResult = new ParticipantResult(dealer, GameResultType.LOSE, 21);

        // when
        participantResult.update(anotherResult);

        // then
        assertThat(participantResult.getCountsOfResultTypes().getOrDefault(GameResultType.LOSE, 0)).isEqualTo(2);
    }

    @Test
    void 특정_참가자의_결과인지_확인할_수_있다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        ParticipantResult participantResult = new ParticipantResult(dealer, GameResultType.LOSE, 21);

        // when
        boolean actual = participantResult.isResultOf(dealer);

        // then
        assertThat(actual).isTrue();
    }
}

package blackjack.domain;

import static blackjack.domain.card.CardSpec.ACE;
import static blackjack.domain.card.CardSpec.JACK;
import static blackjack.domain.card.CardSpec.KING;
import static blackjack.domain.card.CardSpec.QUEEN;
import static blackjack.domain.card.CardSpec.THREE;
import static blackjack.domain.card.CardSpec.TWO;

import blackjack.domain.Result.WinOrLose;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    void result_participantWin() {
        int batMoney = 1000;
        int expectedWinningMoney = 1000;
        Dealer dealer = Dealer.of(TWO.card(), THREE.card());
        Participant participant = Participant.of("nabom", batMoney, JACK.card(), KING.card());
        Participants participants = Participants.of(participant);

        Result result = new Result(dealer, participants);
        Assertions.assertThat(result.winOrLoseResult().get(participant)).isEqualTo(WinOrLose.WIN);

        WinningMoney winningMoney = result.winningMoneyResult().get(0);
        Assertions.assertThat(winningMoney.name()).isEqualTo(participant.name());
        Assertions.assertThat(winningMoney.winningMoney()).isEqualTo(expectedWinningMoney);
    }

    @Test
    void result_participantLose() {
        int batMoney = 1000;
        int expectedWinningMoney = -1000;
        Dealer dealer = Dealer.of(ACE.card(), QUEEN.card());
        Participant participant = Participant.of("nabom", batMoney, JACK.card(), KING.card());
        Participants participants = Participants.of(participant);

        Result result = new Result(dealer, participants);
        Assertions.assertThat(result.winOrLoseResult().get(participant)).isEqualTo(WinOrLose.LOSE);

        WinningMoney winningMoney = result.winningMoneyResult().get(0);
        Assertions.assertThat(winningMoney.name()).isEqualTo(participant.name());
        Assertions.assertThat(winningMoney.winningMoney()).isEqualTo(expectedWinningMoney);
    }

}
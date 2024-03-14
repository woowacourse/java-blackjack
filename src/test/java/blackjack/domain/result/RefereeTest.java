package blackjack.domain.result;

import blackjack.domain.card.Number;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

import blackjack.testutil.ParticipantGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RefereeTest {
    @DisplayName("플레이어가 이긴 경우 WIN을 반환한다.")
    @Test
    void playerWinResultTest() {
        Player player = ParticipantGenerator.createPlayer(List.of(Number.TWO, Number.EIGHT, Number.SIX));
        Dealer dealer = ParticipantGenerator.createDealer(List.of(Number.TWO, Number.FOUR));
        Referee referee = Referee.getInstance();
        HandResult playerResult = referee.getPlayerResult(player, dealer);

        assertThat(playerResult).isEqualTo(HandResult.WIN);
    }

    @DisplayName("플레이어가 진 경우 LOSE를 반환한다.")
    @Test
    void playerLoseTest() {
        Player player = ParticipantGenerator.createPlayer(List.of(Number.SEVEN, Number.TWO));
        Dealer dealer = ParticipantGenerator.createDealer(List.of(Number.QUEEN, Number.SEVEN));
        Referee referee = Referee.getInstance();
        HandResult playerResult = referee.getPlayerResult(player, dealer);

        assertThat(playerResult).isEqualTo(HandResult.LOSE);
    }

    @DisplayName("플레이어와 딜러가 무승부일 경우 DRAW를 반환한다.")
    @Test
    void playerDrawTest() {
        Player player = ParticipantGenerator.createPlayer(List.of(Number.TWO, Number.SIX));
        Dealer dealer = ParticipantGenerator.createDealer(List.of(Number.FOUR, Number.TWO, Number.TWO));
        Referee referee = Referee.getInstance();
        HandResult playerResult = referee.getPlayerResult(player, dealer);

        assertThat(playerResult).isEqualTo(HandResult.DRAW);
    }

    @DisplayName("플레이어가 블랙잭으로 이긴 경우 BLACKJACK_WIN을 반환한다.")
    @Test
    void playerBlackjackWinTest() {
        Player player = ParticipantGenerator.createPlayer(List.of(Number.ACE, Number.JACK));
        Dealer dealer = ParticipantGenerator.createDealer(List.of(Number.FIVE, Number.SIX, Number.ACE));
        Referee referee = Referee.getInstance();
        HandResult playerResult = referee.getPlayerResult(player, dealer);

        assertThat(playerResult).isEqualTo(HandResult.BLACKJACK_WIN);
    }
}

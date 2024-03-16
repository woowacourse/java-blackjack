package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.betting.DealerBetting;
import blackjack.domain.betting.PlayerBetting;
import blackjack.domain.betting.PlayerBettings;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;
import blackjack.domain.result.WinningResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BettingsTest {
    @DisplayName("플레이어의 승패에 따라 최종 수익을 구한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "WIN, 10000, 20000",
            "WIN_BLACKJACK, 15000, 30000",
            "LOSE, -10000, -20000",
            "DRAW, 0, 0"})
    void applyWinStatus(WinStatus winStatus, int pobiExpected, int jasonExpected) {
        // given
        WinningResult winningResult = new WinningResult(Map.of(
                new ParticipantName("pobi"), winStatus,
                new ParticipantName("jason"), winStatus));

        PlayerBetting pobiBetting = PlayerBetting.create("pobi", 10000);
        PlayerBetting jasonBetting = PlayerBetting.create("jason", 20000);
        PlayerBettings playerBettings = new PlayerBettings(List.of(pobiBetting, jasonBetting));

        // when
        PlayerBettings bettingResults = playerBettings.applyWinStatus(winningResult);

        // then
        assertThat(bettingResults.getPlayerBettings().get(0).getBetting()).isEqualTo(pobiExpected);
        assertThat(bettingResults.getPlayerBettings().get(1).getBetting()).isEqualTo(jasonExpected);
    }

    @DisplayName("플레이어의 승패에 따라 딜러의 최종 수익을 구한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "WIN, LOSE, 10000 ",
            "LOSE, WIN, -10000",
            "WIN, DRAW, -10000",
            "DRAW, DRAW, 0"})
    void getDealerResult(WinStatus pobiWinStatus, WinStatus jasonWinStatus, int expeted) {
        // given
        WinningResult winningResult = new WinningResult(Map.of(
                new ParticipantName("pobi"), pobiWinStatus,
                new ParticipantName("jason"), jasonWinStatus));

        PlayerBetting pobiBetting = PlayerBetting.create("pobi", 10000);
        PlayerBetting jasonBetting = PlayerBetting.create("jason", 20000);
        PlayerBettings playerBettings = new PlayerBettings(List.of(pobiBetting, jasonBetting));
        PlayerBettings bettingResults = playerBettings.applyWinStatus(winningResult);

        // when
        DealerBetting dealerResult = DealerBetting.of(bettingResults, new Dealer());

        // then
        assertThat(dealerResult.getBettingMoney().getMoeney()).isEqualTo(expeted);
    }
}

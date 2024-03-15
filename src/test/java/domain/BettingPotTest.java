package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;

class BettingPotTest {

    @DisplayName("사용자와 배팅 기록을 기록한다.")
    @Test
    void collect() {
        BettingPot bettingPot = new BettingPot();
        Player player = new Player("산초");
        Bet bet = new Bet(100);

        assertThatCode(() -> bettingPot.put(player, bet))
                .doesNotThrowAnyException();
    }

    @DisplayName("결과에 따라 사용자의 수익을 정산한다.")
    @ParameterizedTest
    @CsvSource(value = {"DEALER_WIN:10:-10", "PLAYER_WIN:10:10", "PLAYER_BLACK_JACK_WIN:10:15", "PUSH:10:0"}, delimiter = ':')
    void settlePlayer(PlayerGameResult playerGameResult, int betAmount, int expectedSettlement) {
        BettingPot bettingPot = new BettingPot();
        Player player = new Player("산초");
        Bet bet = new Bet(betAmount);
        bettingPot.put(player, bet);
        Map<Player, PlayerGameResult> playerResult = new HashMap<>();
        playerResult.put(player, playerGameResult);

        Map<Player, Integer> settlement = bettingPot.settlePlayerBet(playerResult);
        float actualSettlement = settlement.get(player);

        Assertions.assertThat(actualSettlement).isEqualTo(expectedSettlement);
    }

    @DisplayName("결과에 따라 딜러의 수익을 정산한다.")
    @ParameterizedTest
    @CsvSource(value = {"DEALER_WIN:10:-10", "PLAYER_WIN:10:10", "PLAYER_BLACK_JACK_WIN:10:15", "PUSH:10:0"}, delimiter = ':')
    void settleDealer(PlayerGameResult playerGameResult, int betAmount, int expectedPlayerSettlement) {
        BettingPot bettingPot = new BettingPot();
        Player player = new Player("산초");
        Bet bet = new Bet(betAmount);
        bettingPot.put(player, bet);
        Map<Player, PlayerGameResult> playerResult = new HashMap<>();
        playerResult.put(player, playerGameResult);

        int actualDealerSettlement = bettingPot.settleDealerBet(playerResult);
        int expectedDealerSettlement = expectedPlayerSettlement * -1;

        Assertions.assertThat(actualDealerSettlement).isEqualTo(expectedDealerSettlement);
    }
}

package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.TestHandCreator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.TestPlayerCreator;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerProfitResult;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("베팅 관리자 도메인 테스트")
class BettingBankTest {

    @DisplayName("플레이어들의 통합 수익을 계산할 수 있다")
    @Test
    void testCalculatePlayerProfit() {
        Player player1 = TestPlayerCreator.of("리비", 1, 10);
        Player player2 = TestPlayerCreator.of("썬", 3, 4);
        Dealer dealer = new Dealer(TestHandCreator.of(3, 4, 5));

        Map<Player, BetAmout> playerMoneyMap = new HashMap<>();
        playerMoneyMap.put(player1, new BetAmout(1000));
        playerMoneyMap.put(player2, new BetAmout(1000));
        BettingBank bettingBank = new BettingBank(playerMoneyMap);

        PlayerProfitResult playerProfitResult = bettingBank.calculateProfitResult(dealer);

        assertThat(GameResult.of(dealer, player2)).isEqualTo(GameResult.PLAYER_LOSE);
        assertAll(
                () -> assertThat(playerProfitResult.findProfitOfPlayer(player1).getValue()).isEqualTo(1500),
                () -> assertThat(playerProfitResult.findProfitOfPlayer(player2).getValue()).isEqualTo(-1000)
        );
    }
}

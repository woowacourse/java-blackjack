package blackjack.domain.money;

import blackjack.domain.game.PlayerResult;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("수익 계산기")
public class ProfitCalculatorTest {
    @Test
    @DisplayName("플레이어 결과가 Blackjack이면 베팅 금액의 1.5배를 받는다.")
    void playerBlackjackProfit() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(1000));
        PlayerResult result = PlayerResult.BLACKJACK_WIN;
        ProfitCalculator calculator = new ProfitCalculator();

        // when
        calculator.playerProfit(player, result);

        // then
        assertThat(player.profit()).isEqualTo((long) (1000 * 1.5));
    }

    @Test
    @DisplayName("플레이어 결과가 Win이면 베팅 금액 만큼 받는다.")
    void playerWinProfit() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(1000));
        PlayerResult result = PlayerResult.WIN;
        ProfitCalculator calculator = new ProfitCalculator();

        // when
        calculator.playerProfit(player, result);

        // then
        assertThat(player.profit()).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어 결과가 Lose이면 베팅 금액을 뺴앗긴다.")
    void playerLoseProfit() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(1000));
        PlayerResult result = PlayerResult.LOSE;
        ProfitCalculator calculator = new ProfitCalculator();

        // when
        calculator.playerProfit(player, result);

        // then
        assertThat(player.profit()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("플레이어 결과가 Push이면 수익을 받지 않는다.")
    void playerPushProfit() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(1000));
        PlayerResult result = PlayerResult.PUSH;
        ProfitCalculator calculator = new ProfitCalculator();

        // when
        calculator.playerProfit(player, result);

        // then
        assertThat(player.profit()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러는 전체 플레이어의 추가 금액의 합 * -1 만큼 받는다.")
    void dealerProfit() {
        // given
        Player player1 = new Player(new Name("lemone"), new Chip(1000));
        Player player2 = new Player(new Name("pobi"), new Chip(1000));
        player1.addProfit(1500L);
        player2.addProfit(-1000L);
        Dealer dealer = new Dealer(new Chip(0));
        ProfitCalculator calculator = new ProfitCalculator();

        // when
        calculator.dealerProfit(dealer, new Players(List.of(player1, player2)));

        // then
        assertThat(dealer.profit()).isEqualTo(-500);
    }
}

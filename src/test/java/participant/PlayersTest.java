package participant;

import static org.assertj.core.api.Assertions.assertThat;

import game.GameResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("모든 플레이어의 수익을 합해서 반환한다.")
    void test() {
        // given
        int player1BettingMoney = 10000;
        int player2BettingMoney = 10000;
        Player player1 = new Player("미소", player1BettingMoney);
        Player player2 = new Player("율무", player2BettingMoney);
        Players players = new Players(List.of(player1, player2));
        GameResult player1GameResult = GameResult.WIN;
        GameResult player2GameResult = GameResult.WIN;
        player1.updateMoney(player1GameResult.getRate());
        player2.updateMoney(player2GameResult.getRate());

        // when
        Profit profit = players.sumProfits();

        // then
        assertThat(profit.getAmount())
                .isEqualTo((int) (player1BettingMoney * player1GameResult.getRate()
                        + player2BettingMoney * player2GameResult.getRate()));
    }
}

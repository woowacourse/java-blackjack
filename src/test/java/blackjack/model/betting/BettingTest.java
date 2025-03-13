package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import blackjack.model.MatchResult;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("배팅 테스트")
class BettingTest {

    @DisplayName("플레이어가 돈을 건다")
    @Test
    void placeABetTest() {
        // given
        Player player = new Player("pobi");
        Players players = new Players(List.of(player));
        int money = 5000;
        Betting betting = new Betting();

        // when
        betting.placeABet(players, player, money);

        // then
        assertThat(betting.getBets().get(player))
                .isEqualTo(new BetAmount(money));
    }

    @DisplayName("플레이어가 존재하지 않으면 예외를 발생시킨다.")
    @Test
    void placeABetExceptionTest() {
        // given
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");
        Players players = new Players(List.of(pobi));
        Betting betting = new Betting();

        // when, then
        assertThatCode(() -> betting.placeABet(players, jason, 1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 플레이어입니다.");
    }

    @DisplayName("플레이어가 블랙잭으로 이기면 1.5배의 수익을 얻는다.")
    @Test
    void cashOutTest_whenBlackJack() {
        // given
        Player player = new Player("pobi");
        Players players = new Players(List.of(player));
        Betting betting = new Betting();
        int stake = 1000;
        betting.placeABet(players, player, stake);

        // when
        Profit profit = betting.cashOut(player, MatchResult.BLACKJACK);

        // then
        assertThat(profit.getProfit())
                .isEqualTo(1500);
    }
}
package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.stategy.NoShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("배팅 테이블")
class BettingTableTest {

    @DisplayName("존재하지 않는 사용자의 수익률을 계산하려고 하면 예외가 발생한다.")
    @Test
    void test() {
        // given
        Deck deck = new Deck(new NoShuffleStrategy());
        Dealer dealer = new Dealer(deck);

        Player exist = new Player("choco", dealer);
        Player nonExist = new Player("clover", dealer);

        Map<Player, Betting> playerBetting = new HashMap<>();
        playerBetting.put(exist, new Betting("1000"));

        BettingTable bettingTable = new BettingTable(playerBetting);

        // when & then
        assertThatThrownBy(() -> bettingTable.calculateProfitByPlayer(nonExist, GameResult.WIN))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
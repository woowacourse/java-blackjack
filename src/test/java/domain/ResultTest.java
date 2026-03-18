package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    @DisplayName("딜러 수익은 플레이어 수익의 합에 반대다.")
    void getDealerProfit() {
        Dealer dealer = Dealer.create();
        Player pobi = Player.from("pobi", new Money(10000));
        Player jason = Player.from("jason", new Money(20000));

        Map<Player, MatchResult> playerResults = new LinkedHashMap<>();
        playerResults.put(pobi, MatchResult.LOSE);
        playerResults.put(jason, MatchResult.WIN);

        Result result = new Result(dealer, playerResults);
        assertThat(result.getDealerProfit()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어 수익은 배팅 금액에 배당률을 곱한 값이다.")
    void getPlayerProfit() {
        Dealer dealer = Dealer.create();
        Player pobi = Player.from("pobi", new Money(10000));

        Map<Player, MatchResult> playerResults = new LinkedHashMap<>();
        playerResults.put(pobi, MatchResult.BLACKJACK);

        Result result = new Result(dealer, playerResults);
        assertThat(result.getPlayerProfit(pobi)).isEqualTo(15000);
    }
}

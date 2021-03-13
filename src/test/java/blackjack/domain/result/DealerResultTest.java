package blackjack.domain.result;

import blackjack.domain.Money;
import blackjack.domain.ResultType;
import blackjack.domain.user.Name;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerResultTest {
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    Results results;
    DealerResult dealerResult;

    @BeforeEach
    void setUp() {
        player1 = new Player(Name.of("player1"), Money.of(10000));
        player2 = new Player(Name.of("player2"), Money.of(10000));
        player3 = new Player(Name.of("player3"), Money.of(10000));
        player4 = new Player(Name.of("player4"), Money.of(10000));

        results = Results.of(new HashMap<Player, ResultType>(){
            {
                put(player1, ResultType.BLACKJACK_WIN);
                put(player2, ResultType.WIN);
                put(player3, ResultType.DRAW);
                put(player4, ResultType.LOSE);
            }
        });
        dealerResult = results.generateDealerResult();
    }

    @DisplayName("딜러의 결과는 사용자들의 결과 반대값들의 총합이다.")
    @Test
    void computeDealerResultTest() {
        assertThat(dealerResult.getCountOfResultOf(ResultType.WIN)).isEqualTo(1);
        assertThat(dealerResult.getCountOfResultOf(ResultType.DRAW)).isEqualTo(1);
        assertThat(dealerResult.getCountOfResultOf(ResultType.LOSE)).isEqualTo(2);
    }

    @DisplayName("딜러의 수익은 사용자들 수익의 총합과 반대의 부호를 가진 수가 된다.")
    @Test
    void computeDealerProfitTest() {
        assertThat(dealerResult.getProfit()).isEqualTo(-15000);
    }
}

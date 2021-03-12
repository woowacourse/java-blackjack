package blackjack.domain;

import blackjack.domain.result.Results;
import blackjack.domain.user.Name;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(Name.of("kimkim"), Money.of(10000));
    }

    @DisplayName("이긴 경우 플레이어는 베팅 금액만큼의 수익을 가져간다.")
    @Test
    void computeWinProfitTest() {
        Results results = Results.of(new HashMap<Player, ResultType>(){
            {
                put(player, ResultType.WIN);
            }
        });
        assertThat(results.getEarningMoneyOf(player).toLong()).isEqualTo(10000);
    }

    @DisplayName("비긴 경우 수익은 없다.")
    @Test
    void computeDrawProfitTest() {
        Results results = Results.of(new HashMap<Player, ResultType>(){
            {
                put(player, ResultType.DRAW);
            }
        });
        assertThat(results.getEarningMoneyOf(player).toLong()).isEqualTo(0);
    }

    @DisplayName("진 경우 플레이어는 베팅 금액만큼 잃는다.")
    @Test
    void computeLoseProfitTest() {
        Results results = Results.of(new HashMap<Player, ResultType>(){
            {
                put(player, ResultType.LOSE);
            }
        });
        assertThat(results.getEarningMoneyOf(player).toLong()).isEqualTo(-10000);
    }
}

package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BettingTableTest {
    public static final Player player1 = new Player("pobi");
    public static final Player player2 = new Player("jason");

    private final BettingTable bettingTable = new BettingTable();

    @BeforeEach
    void set() {
        bettingTable.add(player1, new BettingMoney(10000));
        bettingTable.add(player2, new BettingMoney(2000));
    }

    @Test
    @DisplayName("승패결과에 따라 배팅금액 결과를 계산한다.")
    void calculate() {
        Map<Player, ResultType> playerResult = new HashMap<>();
        playerResult.put(player1, ResultType.BLACKJACK);
        playerResult.put(player2, ResultType.LOSE);

        this.bettingTable.calculate(playerResult);

        Map<Player, ResultMoney> bettingTableResult = new HashMap<>();
        bettingTableResult.put(player1, new ResultMoney(15000));
        bettingTableResult.put(player2, new ResultMoney(-2000));
        Assertions.assertThat(bettingTable.getBettingTable()).isEqualTo(bettingTableResult);
    }

    @Test
    @DisplayName("딜러의 최종 수익을 확인한다.")
    void sum() {
        Map<Player, ResultType> playerResult = new HashMap<>();
        playerResult.put(player1, ResultType.LOSE);
        playerResult.put(player2, ResultType.WIN);

        this.bettingTable.calculate(playerResult);

        Assertions.assertThat(bettingTable.getDealerProfit()).isEqualTo(8000);
    }

}

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
        bettingTable.add(player1, new Money(10000));
        bettingTable.add(player2, new Money(2000));
    }

    @Test
    @DisplayName("승패결과에 따라 배팅금액 결과를 계산한다.")
    void calculate() {
        Map<Player, ResultType> playerResult = new HashMap<>();
        playerResult.put(player1, ResultType.BLACKJACK);
        playerResult.put(player2, ResultType.LOSE);

        this.bettingTable.calculate(playerResult);

        Map<Player, Money> bettingTableResult = new HashMap<>();
        bettingTableResult.put(player1, new Money(15000));
        bettingTableResult.put(player2, new Money(-2000));
        Assertions.assertThat(bettingTable.getBettingTable()).isEqualTo(bettingTableResult);
    }

    @Test
    @DisplayName("배팅테이블의 총 금액을 합산한다.")
    void sum() {
        Assertions.assertThat(bettingTable.sum()).isEqualTo(12000);
    }

}

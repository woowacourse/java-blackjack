package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BettingTableTest {
    private final BettingTable bettingTable = new BettingTable();
    private final Map<String, ResultType> playerResult = new HashMap<>();

    @BeforeEach
    void set() {
        bettingTable.add("pobi", new Money(10000));
        bettingTable.add("jason", new Money(2000));
        playerResult.put("pobi", ResultType.BLACKJACK);
        playerResult.put("jason", ResultType.LOSE);
    }


    @Test
    @DisplayName("승패결과에 따라 배팅금액 결과를 계산한다.")
    void calculate() {
        this.bettingTable.calculate(playerResult);

        Map<String, Money> bettingTableResult = new HashMap<>();
        bettingTableResult.put("pobi", new Money(15000));
        bettingTableResult.put("jason", new Money(-2000));
        Assertions.assertThat(bettingTable.getBettingTable()).isEqualTo(bettingTableResult);
    }
    @Test
    @DisplayName("배팅테이블의 총 금액을 합산한다..")
    void sum() {
        Assertions.assertThat(bettingTable.sum()).isEqualTo(12000);
    }

}

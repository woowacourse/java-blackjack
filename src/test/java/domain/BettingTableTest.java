package domain;

import domain.constant.GamerResult;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTableTest {
    @Test
    @DisplayName("입력받은 Gamer들의 결과에 따라 최종 수익을 반환한다")
    void initWithMap() {
        BettingTable bettingTable = new BettingTable(Map.of("test", new BettingAmount("1000")));
        Assertions.assertThat(bettingTable.getTotalProfit(Map.of("test", GamerResult.WIN)))
                .isEqualTo(Map.of(
                        "dealer", -1000,
                        "test", 1000
                ));
    }
}

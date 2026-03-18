package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @Test
    @DisplayName("겜블러 별로 베팅 금액을 저장하고 조회하는 테스트")
    void 겜블러별_베팅_금액을_저장하고_조회_수_있다() {
        // given
        List<String> nameValues = List.of("pobi", "woni");
        Betting betting = new Betting(nameValues);

        // when
        betting.betBettingAmount("pobi", new BettingAmount(BigDecimal.valueOf(10000)));
        betting.betBettingAmount("woni", new BettingAmount(BigDecimal.valueOf(20000)));

        // then
        assertAll(() -> assertThat(betting.getBettingAmountByName("pobi")).isEqualTo(
                        new BettingAmount(BigDecimal.valueOf(10000))),
                () -> assertThat(betting.getBettingAmountByName("woni")).isEqualTo(
                        new BettingAmount(BigDecimal.valueOf(20000))));
    }
}

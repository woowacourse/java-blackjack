package domain.betting;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

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
        betting.betBettingAmount("pobi", new BettingAmount(10000));
        betting.betBettingAmount("woni", new BettingAmount(20000));

        // then
        assertAll(
                () -> assertThat(betting.getBettingAmountByName("pobi"))
                        .isEqualTo(new BettingAmount(10000)),
                () -> assertThat(betting.getBettingAmountByName("woni"))
                        .isEqualTo(new BettingAmount(20000))
        );
    }

}
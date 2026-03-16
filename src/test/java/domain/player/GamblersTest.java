package domain.player;

import domain.betting.BettingAmount;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("이름, 베팅금액 정상 저장 테스트")
    void 겜블러_이름_베팅금액_정상_저장_생성_테스트() {
        // given
        Map<String, BettingAmount> gamblerBettingInfo = new HashMap<String, BettingAmount>() {
        };
        gamblerBettingInfo.put("pobi", new BettingAmount(BigDecimal.valueOf(10000)));
        gamblerBettingInfo.put("coco", new BettingAmount(BigDecimal.valueOf(20000)));

        // when
        Gamblers gamblers = new Gamblers(GamblersFactory.createGamblers(gamblerBettingInfo));

        // then
        Assertions.assertThat(gamblers.getGamblersSize()).isEqualTo(2);
    }
}

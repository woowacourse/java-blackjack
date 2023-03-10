package domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BettingInfoTest {
    @Nested
    class 수익금계산 {
        @Test
        void should_수익금을생성_when_배팅금액과게임결과를통해서() {
            // given
            Map<String, Integer> bettingAmounts = new HashMap<>();
            bettingAmounts.put("에밀", 1000);
            bettingAmounts.put("포이", 1000);
            bettingAmounts.put("오리", 1000);
            bettingAmounts.put("연어", 1000);
            final BettingInfo bettingInfo = new BettingInfo(bettingAmounts);

            // when
            final Map<String, Integer> earnings = bettingInfo.getEarnings(Map.of("에밀", PlayerOutcome.BLACKJACK
                    , "포이", PlayerOutcome.WIN
                    , "오리", PlayerOutcome.DRAW
                    , "연어", PlayerOutcome.LOSS));

            // then
            assertThat(earnings).containsEntry("에밀", 1500)
                                .containsEntry("포이", 1000)
                                .containsEntry("오리", 0)
                                .containsEntry("연어", -1000);
        }
    }
}
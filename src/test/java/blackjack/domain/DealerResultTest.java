package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerResultTest {
    @DisplayName("딜러의 게임 결과를 저장하고 반환한다")
    @Test
    void test1() {
        // given
        DealerResult dealerResult = new DealerResult();

        // when
        dealerResult.addCountOf(GameResultType.WIN);
        dealerResult.addCountOf(GameResultType.TIE);
        dealerResult.addCountOf(GameResultType.LOSE);

        Map<GameResultType, Integer> result = dealerResult.getDealerResult();

        // then
        assertAll(
                () -> assertThat(result.getOrDefault(GameResultType.WIN, 0)).isEqualTo(1),
                () -> assertThat(result.getOrDefault(GameResultType.TIE, 0)).isEqualTo(1),
                () -> assertThat(result.getOrDefault(GameResultType.LOSE, 0)).isEqualTo(1)
        );
    }
}

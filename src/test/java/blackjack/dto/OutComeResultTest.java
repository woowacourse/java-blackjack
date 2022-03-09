package blackjack.dto;

import static blackjack.domain.GameOutcome.DRAW;
import static blackjack.domain.GameOutcome.LOSE;
import static blackjack.domain.GameOutcome.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.GameOutcome;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutComeResultTest {

    @Test
    @DisplayName("딜러의 결과를 반환한다.")
    void getDealerResult() {
        final Map<String, GameOutcome> playerResults = Map.of("a", WIN, "b", WIN);
        final OutComeResult outComeResult = OutComeResult.from(playerResults);
        assertThat(outComeResult.getDealerResult()).contains(entry(LOSE, 2), entry(WIN, 0), entry(DRAW, 0));
    }
}

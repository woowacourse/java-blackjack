package domain.game;

import domain.betting.BettingAmount;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("게임을 처음 시작했을 떄 딜러와 참가자들 각각에게 카드가 2장씩 가는지 검증")
    void 게임_초기화_검증() {
        // given
        Map<String, BettingAmount> tempGamblers = new HashMap<>();
        tempGamblers.put("pobi", new BettingAmount(BigDecimal.valueOf(10000)));
        tempGamblers.put("jason", new BettingAmount(BigDecimal.valueOf(20000)));
        Game game = new Game(tempGamblers);

        // when
        game.initializeGame();

        // then
        Assertions.assertThat(game.getDealerHandSize()).isEqualTo(2);
        Assertions.assertThat(game.getGamblersHandSize()
                        .stream()
                        .allMatch(count -> count == 2))
                .isEqualTo(true);
    }
}

package domain.game;

import static util.Constants.DEALER_NAME;
import static util.Constants.DEFAULT_CARD_SET;
import static util.Constants.DEFAULT_START_CARD_COUNT;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("게임을 처음 시작했을 떄 딜러와 참가자들 각각에게 카드가 2장씩 가는지 검증")
    void 게임_초기화_검증 () {
        // given
        Game game = new Game(DEALER_NAME, List.of("pobi", "jason"), DEFAULT_CARD_SET);

        // when
        game.initializeGame();

        // then
        Assertions.assertThat(game.getDealerHandSize()).isEqualTo(2);
        Assertions.assertThat(game.getGamblersHandSize()
                .stream()
                .allMatch(count -> count == DEFAULT_START_CARD_COUNT))
                        .isEqualTo(true);
    }
}

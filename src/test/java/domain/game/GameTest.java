package domain.game;

import static util.Constants.DEFAULT_CARD_SET;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("게임을 처음 시작했을 떄 딜러와 참가자들 각각에게 카드가 2장씩 가는지 검증")
    void 게임_초기화_검증 () {
        // given
        Game game = new Game(List.of("pobi", "jason"), DEFAULT_CARD_SET);

        // when
        game.initializeGame();

        // then
        Assertions.assertThat(game.getDealer().getCardSize()).isEqualTo(2);
        Assertions.assertThat(game.getGamblers().getGamblersInfo().stream()
                .allMatch(player -> player.getCardSize() == 2)).isEqualTo(true);

    }
}
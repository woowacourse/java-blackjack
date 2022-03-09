package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardFactory;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    @DisplayName("게임을 초기화 한다.")
    void initGame() {
        // give
        Game game = new Game(CardFactory.createNoShuffle(), List.of("pobi", "rick"));

        // when
        final int actual = game.getParticipantCount();

        // then
        assertThat(actual).isEqualTo(3);
    }
}

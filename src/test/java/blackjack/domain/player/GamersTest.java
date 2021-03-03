package blackjack.domain.player;

import blackjack.exception.GamerDuplicateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamersTest {

    @Test
    @DisplayName("이름이 중복 시 예외")
    void createGamers_GamerDuplicateException() {
        Assertions.assertThatThrownBy(
            () -> new Gamers("nabom", "nabom")
        ).isInstanceOf(GamerDuplicateException.class);
    }

    @Test
    void createGamers() {
        Gamers gamers = new Gamers("nabom", "neozal");
        Assertions.assertThat(gamers.getGamers().size()).isEqualTo(2);
    }
}
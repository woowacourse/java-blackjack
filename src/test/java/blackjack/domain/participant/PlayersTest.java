package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("Players를 생성한다")
    @Test
    void create() {
        assertThatCode(() -> new Players(
                List.of(
                        new Player("mark", new Betting(1000)),
                        new Player("pk", new Betting(1000)))))
                .doesNotThrowAnyException();
    }


}

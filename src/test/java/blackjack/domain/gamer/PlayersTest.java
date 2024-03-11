package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다.")
    @Test
    void occurExceptionIfPlayersNameIsDuplicated() {
        assertThatThrownBy(() -> Players.from(List.of("pobi", "pobi", "jason")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.ERROR_DUPLICATED_NAME);
    }

    @DisplayName("이름을 보고 플레이어를 찾는다.")
    @Test
    void findPlayerByName() {
        final Players players = Players.from(List.of("pobi", "jason"));

        final Player actual = players.findByName(new Name("pobi"));

        assertThat(actual.getName()).isEqualTo(new Name("pobi"));
    }
}

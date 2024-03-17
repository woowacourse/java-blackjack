package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다.")
    @Test
    void occurExceptionIfPlayerNameIsDuplicated() {
        assertThatThrownBy(() -> new Players(List.of(
                new Player("pobi", 10000),
                new Player("pobi", 20000),
                new Player("jason", 30000)
                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.ERROR_PLAYER_NAME_DUPLICATED);
    }

    @DisplayName("플레이어 수가 최소 인원 미만이면 예외가 발생한다.")
    @Test
    void occurExceptionIfPlayerCountIsLessThanMinCount() {
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.ERROR_MIN_PLAYER_COUNT);
    }

    @DisplayName("이름을 보고 플레이어를 찾는다.")
    @Test
    void findPlayerByName() {
        final Players players = new Players(List.of(
                new Player("pobi", 10000),
                new Player("jason", 20000)
        ));

        final Player actual = players.findByName(new Name("pobi"));

        assertThat(actual.getName()).isEqualTo(new Name("pobi"));
    }
}

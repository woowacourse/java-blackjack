package domain.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어들은 플레이어 리스트로 생성 된다.")
    void createPlayers() {
        List<Player> players = List.of(new Player(new Name("test1")), new Player(new Name("test2")));
        Assertions.assertThatCode(() -> new Players(players)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어가 8명을 초과하면 예외를 발생한다.")
    void validatePlayerCountException() {
        List<Player> players = IntStream.range(0, 9)
                .mapToObj(number -> new Player(new Name("test%d".formatted(number))))
                .collect(Collectors.toList());

        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최대 8명 입니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 중복되면 예외를 발생한다.")
    void validateDuplicatedNameException() {
        Name test = new Name("test");
        Player expected = new Player(test);

        Assertions.assertThatThrownBy(() -> new Players(List.of(expected, expected)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어들을 반환할 수 있다.")
    void getPlayers() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Assertions.assertThat(players.getPlayers()).isEqualTo(List.of(player));
    }
}

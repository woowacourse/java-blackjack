package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("플레이어들은 ")
public class PlayersTest {
    @Test
    @DisplayName("한 명일 수 있다.")
    void createSingleNameTest() {
        List<String> singleName = List.of("pobi");
        List<Integer> bets = List.of(1000);

        Players players = new Players(singleName, bets);
        UserDto userDto = players.getPlayerDtoByName(new Name("pobi"));

        assertThat(userDto.getName()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("중복될 시 예외처리 된다.")
    void validateDuplicatedNamesTest() {
        List<String> playerNames = List.of("pobi", "neo", "pobi");
        List<Integer> bets = List.of(1000, 2000, 3000);

        assertThatThrownBy(() -> new Players(playerNames, bets))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("5개가 초과될 시 예외처리 된다.")
    void validateNamesSizeTest() {
        List<String> playerNames = List.of("pobi", "neo", "hiiro", "mako", "ako", "split");
        List<Integer> bets = List.of(1000, 2000, 3000, 4000, 5000);

        assertThatThrownBy(() -> new Players(playerNames, bets))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레이어는 최대 5명입니다.");
    }
}

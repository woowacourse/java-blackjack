package blackjack.domain;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("플레이어 이름 중복 검증")
    void validateDuplication() {
        List<Player> players = Arrays.asList(
                new Player("aron", "0"),
                new Player("aron", "10"));
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("참가인원수 검증")
    @ValueSource(strings = {"1", "1,2,3,4,5,6,7,8,9"})
    void validatePlayerCount(String input) {
        List<Player> players = Arrays.stream(input.split(","))
                .map(name -> new Player(name, "0"))
                .collect(Collectors.toList());
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 2명 이상, 8명 이하여합니다.");
    }
}

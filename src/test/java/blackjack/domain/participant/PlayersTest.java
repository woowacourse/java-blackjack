package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("참가자 생성 성공")
    void createPlayersSucceed() {
        Players players = new Players(Arrays.asList(
                new Player(new Nickname("air")),
                new Player(new Nickname("picka"))
        ));

        assertThat(players.getPlayers()).hasSize(2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "pika, ,air", ", , , ,"})
    @DisplayName("참가자 생성 실패")
    void createPlayersFail(String input) {
        assertThatThrownBy(() -> {
            List<Player> allPlayers = Arrays.stream(input.split(","))
                    .map(s -> s.replaceAll(" ", ""))
                    .map(Nickname::new)
                    .map(Player::new)
                    .collect(Collectors.toList());
            new Players(allPlayers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("참가자 8명 초과 시 에러 발생")
    void checkPlayersMaximumCount() {
        assertThatThrownBy(() -> {
            List<Player> allPlayers = Arrays.stream("pika, air, a, b, c, d, e, f, g".split(","))
                    .map(s -> s.replaceAll(" ", ""))
                    .map(Nickname::new)
                    .map(Player::new)
                    .collect(Collectors.toList());
            new Players(allPlayers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 참가자 에러 발생")
    void checkDuplicatePlayer() {
        assertThatThrownBy(() -> {
            new Players(Arrays.asList(
                    new Player(new Nickname("air")),
                    new Player(new Nickname("air"))
            ));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("참가자 2명 미만 시 에러 발생")
    void checkPlayersMinimumCount() {
        assertThatThrownBy(() -> {
            List<Player> allPlayers = Arrays.stream("air".split(","))
                    .map(s -> s.replaceAll(" ", ""))
                    .map(Nickname::new)
                    .map(Player::new)
                    .collect(Collectors.toList());
            new Players(allPlayers);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

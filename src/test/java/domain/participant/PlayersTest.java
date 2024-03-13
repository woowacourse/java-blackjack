package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.participant.player.Player;
import domain.participant.player.Players;

class PlayersTest {

    static Stream<Arguments> validateSize() {
        return Stream.of(
                Arguments.of(
                        List.of(),
                        "플레이어의 수는 최소 1명 최대 8명입니다 : 현재 0명"
                ),
                Arguments.of(
                        List.of(
                                new Player("1"), new Player("2"), new Player("3"), new Player("4"),
                                new Player("5"), new Player("6"), new Player("7"), new Player("8"),
                                new Player("9")),
                        "플레이어의 수는 최소 1명 최대 8명입니다 : 현재 9명"
                )
        );
    }

    @DisplayName("플레이어의 수가 최소 1명 최대 8명으로 이루어져 있지 않은 경우 예외가 발생한다.")
    @MethodSource
    @ParameterizedTest
    void validateSize(List<Player> players, String message) {
        assertThatThrownBy(() -> Players.from(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }
}

package domain.participant.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    static Stream<Arguments> validateSize() {
        return Stream.of(
                Arguments.of(
                        List.of(),
                        "플레이어의 수는 최소 1명 최대 8명입니다 : 현재 0명"),
                Arguments.of(
                        List.of(
                                PlayerFixture.of("1", 1000), PlayerFixture.of("2", 1000),
                                PlayerFixture.of("3", 1000), PlayerFixture.of("4", 1000),
                                PlayerFixture.of("5", 1000), PlayerFixture.of("6", 1000),
                                PlayerFixture.of("7", 1000), PlayerFixture.of("8", 1000),
                                PlayerFixture.of("9", 1000)),
                        "플레이어의 수는 최소 1명 최대 8명입니다 : 현재 9명"
                ));
    }

    @DisplayName("플레이어의 수가 1~8명이 아닌 경우 예외를 던진다.")
    @MethodSource
    @ParameterizedTest
    void validateSize(List<Player> players, String message) {
        assertThatThrownBy(() -> Players.from(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }
}

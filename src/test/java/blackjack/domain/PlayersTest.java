package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("플레이어가 1~7명이면 정상적으로 생성된다.")
    void createPlayers() {
        List<String> inputNames = List.of("gray", "luca", "pobi", "neo", "hoy");

        Players players = Players.create(inputNames);

        assertThat(players).isNotNull();
    }

    @Test
    @DisplayName("플레이어 이름이 중복되면 예외가 발생한다.")
    void createPlayersWithDuplicatedName() {
        List<String> inputNames = List.of("gray", "gray", "pobi", "neo", "hoy");

        assertThatThrownBy(() -> Players.create(inputNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("generatePlayerNames")
    @DisplayName("플레이어가 1~7명 이외의 경우에 예외가 발생한다 ")
    void createPlayersWrongSize(List<String> inputNames) {
        assertThatThrownBy(() -> Players.create(inputNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> generatePlayerNames() {
        return Stream.of(
                Arguments.of(Collections.EMPTY_LIST),
                Arguments.of(List.of("a", "b", "c", "d", "e", "f", "g", "h"))
        );
    }
}

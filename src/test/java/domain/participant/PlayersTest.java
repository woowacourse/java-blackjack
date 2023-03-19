package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayersTest {

    @MethodSource(value = "domain.helper.ParticipantTestHelper#validPlayerNames")
    @ParameterizedTest(name = "create()는 유효한 수의 플레이어 이름 컬렉션을 받으면, 예외가 발생하지 않는다")
    void create_givenPlayerNames_thenSuccess(final List<String> playerNames) {
        final Players players = assertDoesNotThrow(() -> Players.create(playerNames));

        assertThat(players)
                .isInstanceOf(Players.class);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#invalidPlayerNames")
    @ParameterizedTest(name = "create()는 7명 초과의 플레이어 이름 컬렉션을 받으면, 예외가 발생한다")
    void create_givenPlayerNames_thenFail(final List<String> playerNames) {
        assertThatThrownBy(() -> Players.create(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최소 1명, 최대 7명이어야 합니다.");
    }

    @Test
    @DisplayName("create()는 중복된 플레이어 이름을 받으면, 예외가 발생한다")
    void create_givenDuplicateNames_thenFail() {
        final List<String> duplicateNames = List.of("a", "b", "c", "d", "e", "a");

        assertThatThrownBy(() -> Players.create(duplicateNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }
}

package domain.participant;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParticipantsTest {

    @MethodSource(value = "validPlayerNames")
    @ParameterizedTest(name = "create()는 유효한 수의 플레이어 이름 컬렉션을 받으면, 예외가 발생하지 않는다")
    void create_givenPlayerNames_thenSuccess(final List<String> playerNames) {
        assertThatCode(() -> Participants.create(playerNames))
                .doesNotThrowAnyException();

        assertThat(Participants.create(playerNames))
                .isInstanceOf(Participants.class);
    }

    @MethodSource(value = "invalidPlayerNames")
    @ParameterizedTest(name = "create()는 7명 초과의 플레이어 이름 컬렉션을 받으면, 예외가 발생한다")
    void create_givenPlayerNames_thenFail(final List<String> playerNames) {
        assertThatThrownBy(() -> Participants.create(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최소 1명, 최대 7명이어야 합니다.");
    }

    private static Stream<Arguments> validPlayerNames() {
        return Stream.of(
                Arguments.of(List.of("zeeto")),
                Arguments.of(List.of("zeeto", "journey", "pobi", "neo", "lisa", "wonnie", "cron"))
        );
    }

    private static Stream<Arguments> invalidPlayerNames() {
        return Stream.of(
                Arguments.of(List.of("zeeto", "journey", "pobi", "neo", "lisa", "wonnie", "cron", "juno"))
        );
    }
}

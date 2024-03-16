package blackjack.model.participant;

import static blackjack.model.participant.Players.CAN_NOT_DUPLICATED_NAME;
import static blackjack.model.participant.Players.OUT_OF_PLAYERS_SIZE_BOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름으로 플레이어 그룹을 생성한다.")
    void createPlayers() {
        Players players = Fixtures.createPlayersWithNames(List.of("리브", "몰리"));

        assertThat(players.getNames()).containsExactly("리브", "몰리");
    }

    @Test
    @DisplayName("플레이어의 이름이 중복되는 경우 예외를 던진다.")
    void createPlayersByDuplicatedName() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.from(List.of("몰리", "몰리")))
                .withMessage(CAN_NOT_DUPLICATED_NAME);
    }

    @ParameterizedTest
    @MethodSource("InvalidNames")
    @DisplayName("플레이어의 이름이 1개 이상 10개 이하가 아니면 예외를 던진다.")
    void createPlayersByOutBound(List<String> names) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.from(names))
                .withMessage(OUT_OF_PLAYERS_SIZE_BOUND);
    }

    private static Stream<Arguments> InvalidNames() {
        return Stream.of(
                Arguments.arguments(List.of()),
                Arguments.arguments(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"))
        );
    }

    static class Fixtures {
        static Players createPlayersWithNames(List<String> names) {
            return Players.from(names);
        }
    }
}

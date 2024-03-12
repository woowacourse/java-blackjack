package blackjack.model.participant;

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
        List<String> names = List.of("리브", "몰리");
        Players players = Players.from(names);
        assertThat(players.getNames()).containsExactly("리브", "몰리");
    }

    @Test
    @DisplayName("플레이어의 이름이 중복되는 경우 예외를 던진다.")
    void createPlayersByDuplicatedName() {
        List<String> names = List.of("몰리", "몰리");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.from(names))
                .withMessage("중복되는 이름을 입력할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("InvalidNames")
    @DisplayName("플레이어의 이름이 1개 이상 10개 이하가 아니면 예외를 던진다.")
    void createPlayersByOutBound(List<String> names) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.from(names))
                .withMessage("참여할 인원의 수는 최소 1명 최대 10명이어야 합니다.");
    }

    private static Stream<Arguments> InvalidNames() {
        return Stream.of(
                Arguments.arguments(List.of()),
                Arguments.arguments(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"))
        );
    }
}

package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlayersTest {

    private static Stream<Arguments> provideFailPlayerNames() {
        List<Player> eightPlayers = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            eightPlayers.add(new Player(Integer.toString(i)));
        }
        return Stream.of(
                Arguments.of(eightPlayers),
                Arguments.of(List.of()));
    }

    @ParameterizedTest
    @DisplayName("플레이어의 수가 1명 미만 혹은 7명 초과일 시 예외 처리 테스트")
    @MethodSource("provideFailPlayerNames")
    void validatePlayerCountTest(List<Player> input) {
        assertThatThrownBy(() -> new Players(input)).isInstanceOf(IllegalArgumentException.class);
    }
}
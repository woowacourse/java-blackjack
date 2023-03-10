package blackjack.domain.participants;

import static blackjack.domain.participants.PlayerTest.BETTING_MONEY;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayersTest {

    private static Stream<List<Player>> playersCountOutOfBounds() {
        return Stream.of(
                List.of(new Player("test", BETTING_MONEY)),
                Collections.nCopies(9, new Player("test", BETTING_MONEY))
        );
    }

    @DisplayName("플레이어 인원수는 최소 2명, 최대 8명이다.")
    @ParameterizedTest(name = "인원수 예외 케이스 {index}")
    @MethodSource("playersCountOutOfBounds")
    void should_ThrowException_When_PlayersCountOutOfBounds(final List<Player> players) {
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 인원 수는 최소 2명 최대 8명입니다.");
    }
}

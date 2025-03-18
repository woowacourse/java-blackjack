package blackjack.domain.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    void 플레이어들을_생성할_수_있다() {
        // given
        final List<String> playerNames = List.of("돔푸", "메이");

        // expected
        Assertions.assertDoesNotThrow(() -> Players.from(playerNames));
    }

    @Test
    void 중복된_플레이어가_존재하는_경우_예외를_발생시킨다() {
        // given
        final List<String> playerNames = List.of("돔푸", "메이", "돔푸");

        // expected
        assertThatThrownBy(() -> Players.from(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("providePlayers")
    void 플레이어_인원수는_1명부터_6명까지_입니다(List<String> playerNames) {
        // expected
        assertThatThrownBy(() -> Players.from(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 인원수는 1명부터 6명까지 입니다.");
    }

    private static Stream<Arguments> providePlayers() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of("메이", "돔푸", "리사", "포라", "밍곰", "멍구", "가이온"))
        );
    }
}

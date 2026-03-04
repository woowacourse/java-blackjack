package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameTest {

    @Nested
    @DisplayName("정상 경우")
    class success {
        @DisplayName("모든 플레이어가 정상적으로 생성된다.")
        @Test
        public void 모든_플레이어가_정상적으로_생성된다() {
            List<String> names = List.of("피즈", "스타크");

            //given
            Game game = new Game(names);
            //when
            Set<Player> players = game.getPlayers();
            Set<String> playerNames = players.stream()
                    .map(Player::getName)
                    .collect(Collectors.toSet());
            //then
            assertThat(playerNames).contains("피즈");
            assertThat(playerNames).contains("스타크");
        }
    }

    @Nested
    @DisplayName("예외 경우")
    class failure {

        @DisplayName("플레이어 이름이 중복된 경우 예외가 발생한다.")
        @Test
        void 플레이어_이름이_중복된_경우_예외가_발생한다() {
            List<String> duplicatedNames = List.of("피즈", "피즈", "스타크");

            assertThatThrownBy(() -> new Game(duplicatedNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }

        private static Stream<Arguments> playerNumberOutOfRange() {
            return Stream.of(
                    Arguments.of(List.of()),
                    Arguments.of(List.of("피즈1","피즈2","피즈3","피즈4","피즈5","피즈6","피즈7","피즈8"))
            );
        }

        @DisplayName("게임에 참여하는 플레이어 인원이 1~7명이 아닐 경우 예외가 발생한다.")
        @ParameterizedTest
        @MethodSource("playerNumberOutOfRange")
        void 게임_참여_플레이어가_1명에서_7명_사이가_아닐_경우_예외가_발생한다(List<String> names) {
            assertThatThrownBy(() -> new Game(names))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 플레이어는 1명 이상 7명 이하여야 합니다.");
        }
    }
}

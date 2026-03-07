package domain.participant;

import static message.ErrorMessage.PLAYER_NAME_DUPLICATED;
import static message.ErrorMessage.PLAYER_NUMBER_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @DisplayName("모든 플레이어가 정상적으로 생성된다.")
    @Test
    void 모든_플레이어가_정상적으로_생성된다() {
        //given
        Players players = new Players(List.of("피즈", "스타크"));

        //when
        List<String> allPlayersName = players.getAllPlayersName();

        //then
        Assertions.assertThat(allPlayersName).contains("피즈");
        Assertions.assertThat(allPlayersName).contains("스타크");
    }


    @Nested
    @DisplayName("예외 경우")
    class failure {

        @DisplayName("플레이어 이름이 중복된 경우 예외가 발생한다.")
        @Test
        void 플레이어_이름이_중복된_경우_예외가_발생한다() {
            List<String> duplicatedNames = List.of("피즈", "피즈", "스타크");

            assertThatThrownBy(() -> new Players(duplicatedNames))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(PLAYER_NAME_DUPLICATED.getMessage());
        }

        private static Stream<Arguments> playerNumberOutOfRange() {
            return Stream.of(
                    Arguments.of(List.of()),
                    Arguments.of(List.of("피즈1", "피즈2", "피즈3", "피즈4", "피즈5", "피즈6", "피즈7", "피즈8"))
            );
        }

        @DisplayName("게임에 참여하는 플레이어 인원이 1~7명이 아닐 경우 예외가 발생한다.")
        @ParameterizedTest
        @MethodSource("playerNumberOutOfRange")
        void 게임_참여_플레이어가_1명에서_7명_사이가_아닐_경우_예외가_발생한다(List<String> names) {
            assertThatThrownBy(() -> new Players(names))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(PLAYER_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }
}
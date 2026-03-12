package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.BlackjackException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @Nested
    class ConstructorTest {

        @Nested
        class Success {

            @Test
            void 플레이어_이름_목록으로_Players를_생성한다() {

                // given
                List<String> input = List.of("jacob", "seoye");

                // when
                Players players = new Players(input);

                // then
                assertThat(players.getPlayers()).hasSize(2);
                assertThat(players.getPlayers())
                        .extracting(Player::getName)
                        .containsExactly("jacob", "seoye");
            }
        }

        @Nested
        class Fail {

            @Test
            void 플레이어_이름이_중복되면_예외가_발생한다() {

                // given
                List<String> input = List.of("jacob", "jacob");

                // when & then
                assertThatThrownBy(() -> new Players(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.PLAYER_DUPLICATED);
            }

            @ParameterizedTest
            @MethodSource("playerCountOutOfRangeCases")
            void 플레이어_수가_2명_미만_또는_8명_초과면_예외가_발생한다(List<String> input) {

                // when & then
                assertThatThrownBy(() -> new Players(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(BlackjackException.ERROR_PREFIX + Players.PLAYER_COUNT_OUT_OF_RANGE);
            }

            static Stream<Arguments> playerCountOutOfRangeCases() {
                return Stream.of(
                        Arguments.of(List.of("a1")),
                        Arguments.of(List.of("a1", "b2", "c3", "d4", "e5", "f6", "g7", "h8", "i9"))
                );
            }
        }
    }

    @Nested
    class GetPlayersTest {

        @Nested
        class Success {

            @Test
            void 플레이어_목록은_수정_불가능한_리스트여야_한다() {

                // given
                Players players = new Players(List.of("jacob", "seoye"));

                // when & then
                assertThatThrownBy(() -> players.getPlayers().add(new Player("brown")))
                        .isInstanceOf(UnsupportedOperationException.class);
            }
        }
    }

    @Nested
    class GetPlayerTest {

        @Nested
        class Success {

            @Test
            void 이름으로_플레이어를_조회한다() {

                // given
                Players players = new Players(List.of("jacob", "seoye"));

                // when
                Player actual = players.getPlayer("jacob");

                // then
                assertThat(actual).isNotNull();
                assertThat(actual.getName()).isEqualTo("jacob");
            }

            @Test
            void 없는_이름을_조회하면_null을_반환한다() {

                // given
                Players players = new Players(List.of("jacob", "seoye"));

                // when
                Player actual = players.getPlayer("brown");

                // then
                assertThat(actual).isNull();
            }
        }
    }
}

package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @DisplayName("플레이어는 1명 이상 4명 이하여야 한다.")
    @ParameterizedTest
    @MethodSource("methodSources")
    void 플레이어는_1명_이상_4명_이하여야_한다(final List<Nickname> nicknames) {

        // given
        final List<Player> playerGroup = nicknames.stream().map(Player::new).toList();

        // when & then
        assertThatCode(() -> new Players(playerGroup))
                .doesNotThrowAnyException();


    }

    @DisplayName("플레이어는 1명 이상 4명 이하가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("methodSources2")
    void 플레이어는_1명_이상_4명_이하가_아니라면_예외가_발생한다(final List<Nickname> nicknames) {

        // given
        final List<Player> playerGroup = nicknames.stream().map(Player::new).toList();

        // when & then
        assertThatThrownBy(() -> new Players(playerGroup))
                .isInstanceOf(IllegalArgumentException.class);


    }

    private static Stream<Arguments> methodSources() {
        return Stream.of(
                Arguments.arguments(List.of(new Nickname("체체"))),
                Arguments.arguments(List.of(new Nickname("체체"), new Nickname("as"))),
                Arguments.arguments(
                        List.of(new Nickname("체체"), new Nickname("as"), new Nickname("aaa"), new Nickname("bvbb")))
        );
    }

    private static Stream<Arguments> methodSources2() {
        return Stream.of(
                Arguments.arguments(List.of()),
                Arguments.arguments(
                        List.of(new Nickname("체체"), new Nickname("as"), new Nickname("aaa"), new Nickname("bvbb"),
                                new Nickname("qqq")))
        );
    }


}

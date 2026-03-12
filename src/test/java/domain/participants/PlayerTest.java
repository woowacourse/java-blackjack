package domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.TestFixture;
import domain.bet.Betting;
import domain.card.vo.Rank;
import domain.hitStrategy.UntilBustHitStrategy;
import domain.state.finished.BlackJack;
import domain.state.running.Hit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    public static Stream<Arguments> canDraw() {
        return Stream.of(
                Arguments.of(TestFixture.createDefaultPlayerByRank(List.of(Rank.KING, Rank.SEVEN)), Hit.class),
                Arguments.of(TestFixture.createDefaultPlayerByRank(List.of(Rank.KING, Rank.SIX)), Hit.class),
                Arguments.of(TestFixture.createDefaultPlayerByRank(List.of(Rank.KING, Rank.ACE)), BlackJack.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    void canDraw(Player player, Class<?> clazz) {
        assertThat(player.getState()).isInstanceOf(clazz);
    }

    @Nested
    @DisplayName("constructor(): ")
    class Constructor {

        @ParameterizedTest
        @DisplayName("이름이 2~7자가 아닐경우 예외를 반환한다.")
        @CsvSource({
                "밥",
                "12345678",
        })
        void player(String name) {
            assertThatThrownBy(
                    () -> new Player(name, TestFixture.createHandByRank(List.of(Rank.ACE, Rank.KING)), new Betting(0),
                            new UntilBustHitStrategy()))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

}

package blackjack.domain.gamer;

import blackjack.domain.betting.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @DisplayName("플레이어의 수가 1 이상 또는 8 이하이면 정상적으로 생성된다.")
    @ParameterizedTest
    @MethodSource("playersSizeSuccessTestArguments")
    void playersSizeSuccessTest(List<Name> playerNames, List<BettingMoney> bettingMonies) {
        assertThatCode(() -> new Players(playerNames, bettingMonies))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> playersSizeSuccessTestArguments() {
        return Stream.of(
                Arguments.arguments(List.of(new Name("p1")), List.of(new BettingMoney(0))),
                Arguments.arguments(List.of(new Name("p1"), new Name("p2"), new Name("p3"), new Name("p4"),
                                new Name("p5"), new Name("p6"), new Name("p7"), new Name("p8")),
                        List.of(new BettingMoney(0), new BettingMoney(0), new BettingMoney(0), new BettingMoney(0),
                                new BettingMoney(0), new BettingMoney(0), new BettingMoney(0), new BettingMoney(0)))
        );
    }

    @DisplayName("플레이어의 수가 1 미만 또는 8 초과이면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("playersSizeErrorTestArguments")
    void playersSizeErrorTest(List<Name> playerNames, List<BettingMoney> bettingMonies) {
        assertThatThrownBy(() -> new Players(playerNames, bettingMonies))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 1 이상 또는 8 이하이어야 합니다.");
    }

    private static Stream<Arguments> playersSizeErrorTestArguments() {
        return Stream.of(
                Arguments.arguments(List.of(), List.of()),
                Arguments.arguments(List.of(new Name("p1"), new Name("p2"), new Name("p3"), new Name("p4"),
                                new Name("p5"), new Name("p6"), new Name("p7"), new Name("p8"), new Name("p9")),
                        List.of(new BettingMoney(0), new BettingMoney(0), new BettingMoney(0), new BettingMoney(0),
                                new BettingMoney(0), new BettingMoney(0), new BettingMoney(0), new BettingMoney(0), new BettingMoney(0)))
        );
    }

    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다.")
    @Test
    void playersNameDuplicateErrorTest() {
        List<Name> names = List.of(new Name("p1"), new Name("p1"));
        List<BettingMoney> bettingMonies = List.of(new BettingMoney(0), new BettingMoney(0));
        assertThatThrownBy(() -> new Players(names, bettingMonies))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.DUPLICATION_ERROR_MESSAGE);
    }
}

package blackjackgame.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("플레이어의 이름은 ")
class NamesTest {
    @DisplayName("중복되는 이름이 존재하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("generateDuplicatedNames")
    void generatePlayersWithDuplicatedName(List<String> playerNames) {
        assertThrows(IllegalArgumentException.class, () -> new Names(playerNames));
    }

    static Stream<Arguments> generateDuplicatedNames() {
        return Stream.of(
                Arguments.of(List.of("중복이름", "중복이름")),
                Arguments.of(List.of("민트", "민트")),
                Arguments.of(List.of("유자", "민트", "맛있다", "맛있다"))
        );
    }

    @DisplayName("존재하지 않으면 예외가 발생한다. ")
    @Test
    void generateEmptyPlayers() {
        assertThrows(IllegalArgumentException.class, () -> new Players(new Names(Collections.emptyList()), List.of()));
    }

    @DisplayName("5개를 초과하면 예외가 발생한다. ")
    @ParameterizedTest
    @MethodSource("generateTooManyPlayers")
    void generateTooManyPlayersTest(List<String> playerNames) {
        assertThrows(IllegalArgumentException.class, () -> new Names(playerNames));
    }

    static Stream<Arguments> generateTooManyPlayers() {
        return Stream.of(
                Arguments.of(List.of("하나", "둘", "셋", "넷", "다섯", "여섯")),
                Arguments.of(List.of("스타벅스", "유자", "민트", "티", "맛있어요", "드셔보세요"))
        );
    }

    @DisplayName("\"딜러\"가 될 수 없다.")
    @Test
    void generatePlayerName_sameAsDealerName() {
        assertThrows(IllegalArgumentException.class, () -> new Names(List.of("민트","딜러")));
    }
}

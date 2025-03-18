package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTypeTest {

    static final int INITIAL_CARD_COUNT = 2;
    static final int NOT_INITIAL_CARD_COUNT = 4;
    static final int WIN_POINT = 21;
    static final int LOSE_POINT = 20;
    static final int BUST_POINT = 22;

    @ParameterizedTest
    @DisplayName("플레이어의 카드점수와 딜러의 카드점수를 통해 승패타입을 구할 수 있다.")
    @MethodSource()
    void canParse(int playerCardCount, int playerPoint, int dealerPoint, GameResultType expectedType) {
        GameResultType actualType = GameResultType.parse(playerCardCount, playerPoint, dealerPoint);
        assertThat(actualType).isEqualTo(expectedType);
    }

    static Stream<Arguments> canParse() {
        return Stream.of(
                Arguments.of(INITIAL_CARD_COUNT, WIN_POINT, WIN_POINT, GameResultType.WIN_WITH_INITIAL_HAND_BLACKJACK),
                Arguments.of(NOT_INITIAL_CARD_COUNT, WIN_POINT, LOSE_POINT, GameResultType.WIN),
                Arguments.of(NOT_INITIAL_CARD_COUNT, LOSE_POINT, WIN_POINT, GameResultType.LOSE),
                Arguments.of(NOT_INITIAL_CARD_COUNT, WIN_POINT, WIN_POINT, GameResultType.DRAW),
                Arguments.of(NOT_INITIAL_CARD_COUNT, WIN_POINT, BUST_POINT, GameResultType.WIN),
                Arguments.of(NOT_INITIAL_CARD_COUNT, BUST_POINT, WIN_POINT, GameResultType.LOSE));
    }
}
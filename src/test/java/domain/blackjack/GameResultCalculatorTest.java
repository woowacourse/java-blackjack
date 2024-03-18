package domain.blackjack;

import static domain.blackjack.GameResult.LOSE;
import static domain.blackjack.GameResult.TIE;
import static domain.blackjack.GameResult.WIN;
import static domain.blackjack.GameResult.WIN_BLACK_JACK;
import static domain.blackjack.TestHoldingCards.BLACK_JACK;
import static domain.blackjack.TestHoldingCards.DEAD_CARDS;
import static domain.blackjack.TestHoldingCards.ONLY_SEVEN_HEART;
import static domain.blackjack.TestHoldingCards.ONLY_SIX_HEART;
import static domain.blackjack.TestHoldingCards.TWO_SIX_CARDS;
import static domain.blackjack.TestHoldingCards.WIN_CARDS_WITHOUT_ACE;
import static domain.blackjack.TestHoldingCards.WIN_CARDS_WITH_ACE;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultCalculatorTest {

    public static Stream<Arguments> getGameResultParameters() {
        return Stream.of(
                Arguments.of(ONLY_SEVEN_HEART, ONLY_SIX_HEART, WIN),
                Arguments.of(ONLY_SIX_HEART, ONLY_SEVEN_HEART, LOSE),
                Arguments.of(ONLY_SEVEN_HEART, ONLY_SEVEN_HEART, TIE),
                Arguments.of(DEAD_CARDS, ONLY_SEVEN_HEART, LOSE),
                Arguments.of(ONLY_SEVEN_HEART, DEAD_CARDS, WIN),
                Arguments.of(DEAD_CARDS, DEAD_CARDS, WIN),
                Arguments.of(WIN_CARDS_WITH_ACE, BLACK_JACK, LOSE),
                Arguments.of(BLACK_JACK, TWO_SIX_CARDS, WIN_BLACK_JACK),
                Arguments.of(BLACK_JACK, WIN_CARDS_WITHOUT_ACE, WIN_BLACK_JACK),
                Arguments.of(BLACK_JACK, WIN_CARDS_WITH_ACE, WIN_BLACK_JACK),
                Arguments.of(BLACK_JACK, BLACK_JACK, TIE)
        );
    }

    @ParameterizedTest
    @MethodSource("getGameResultParameters")
    @DisplayName("승부가 잘 결정되는지 검증")
    void calculate(HoldingCards holdingCards1, HoldingCards holdingCards2, GameResult expected) {
        Player player = Player.from("플레이어", holdingCards1, 1000);
        Dealer dealer = Dealer.of(holdingCards2);
        Assertions.assertThat(GameResultCalculator.calculate(player, dealer))
                .isEqualTo(expected);
    }
}

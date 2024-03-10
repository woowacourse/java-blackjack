package domain.blackjack;

import static domain.blackjack.GameResult.LOSE;
import static domain.blackjack.GameResult.TIE;
import static domain.blackjack.GameResult.WIN;
import static domain.card.Card.ACE_HEART;
import static domain.card.Card.JACK_HEART;
import static domain.card.Card.NINE_HEART;
import static domain.card.Card.QUEEN_HEART;
import static domain.card.Card.SEVEN_HEART;
import static domain.card.Card.SIX_DIAMOND;
import static domain.card.Card.SIX_HEART;
import static domain.card.Card.TWO_HEART;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultCalculatorTest {
    private static final HoldingCards ONLY_SIX_HEART = HoldingCards.of(SIX_HEART);
    private static final HoldingCards ONLY_SEVEN_HEART = HoldingCards.of(SEVEN_HEART);
    private static final HoldingCards DEAD_CARDS = HoldingCards.of(TWO_HEART, JACK_HEART, QUEEN_HEART);

    private static final HoldingCards WIN_CARDS_WITH_ACE = HoldingCards.of(ACE_HEART, QUEEN_HEART);

    private static final HoldingCards WIN_CARDS_WITHOUT_ACE = HoldingCards.of(TWO_HEART, JACK_HEART, NINE_HEART);

    private static final HoldingCards TWO_SIX_CARDS = HoldingCards.of(SIX_HEART, SIX_DIAMOND);

    public static Stream<Arguments> getGameResultParameters() {
        return Stream.of(
                Arguments.of(new Gamer("게이머1", ONLY_SEVEN_HEART), new Gamer("게이머2", ONLY_SIX_HEART), WIN),
                Arguments.of(new Gamer("게이머1", ONLY_SIX_HEART), new Gamer("게이머2", ONLY_SEVEN_HEART), LOSE),
                Arguments.of(new Gamer("게이머1", ONLY_SEVEN_HEART), new Gamer("게이머2", ONLY_SEVEN_HEART), TIE),
                Arguments.of(new Gamer("게이머1", DEAD_CARDS), new Gamer("게이머2", ONLY_SEVEN_HEART), LOSE),
                Arguments.of(new Gamer("게이머1", ONLY_SEVEN_HEART), new Gamer("게이머2", DEAD_CARDS), WIN),
                Arguments.of(new Gamer("게이머1", DEAD_CARDS), new Gamer("게이머2", DEAD_CARDS), TIE),
                Arguments.of(new Gamer("게이머1", WIN_CARDS_WITH_ACE), new Gamer("게이머2", TWO_SIX_CARDS), WIN),
                Arguments.of(new Gamer("게이머1", WIN_CARDS_WITH_ACE), new Gamer("게이머2", WIN_CARDS_WITHOUT_ACE), TIE)
        );
    }

    @ParameterizedTest
    @MethodSource("getGameResultParameters")
    @DisplayName("승부가 잘 결정되는지 검증")
    void calculate(Gamer gamer1, Gamer gamer2, GameResult expected) {
        Assertions.assertThat(GameResultCalculator.calculate(gamer1, gamer2))
                .isEqualTo(expected);
    }
}

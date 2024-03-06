package domain;

import static domain.GameResult.LOSE;
import static domain.GameResult.TIE;
import static domain.GameResult.WIN;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultCalculatorTest {
    private static final HoldingCards ONLY_SIX_HEART = HoldingCards.of(new Card(CardName.SIX, CardType.HEART));
    private static final HoldingCards ONLY_SEVEN_HEART = HoldingCards.of(new Card(CardName.SEVEN, CardType.HEART));
    private static final HoldingCards DEAD_CARDS = HoldingCards.of(
            new Card(CardName.JACK, CardType.HEART),
            new Card(CardName.QUEEN, CardType.HEART),
            new Card(CardName.TWO, CardType.HEART)
    );

    private static final HoldingCards WIN_CARDS_WITH_ACE = HoldingCards.of(
            new Card(CardName.ACE, CardType.HEART),
            new Card(CardName.QUEEN, CardType.HEART)
    );

    private static final HoldingCards WIN_CARDS_WITHOUT_ACE = HoldingCards.of(
            new Card(CardName.JACK, CardType.HEART),
            new Card(CardName.NINE, CardType.HEART),
            new Card(CardName.TWO, CardType.HEART)
    );

    private static final HoldingCards TWO_SIX_CARDS = HoldingCards.of(
            new Card(CardName.SIX, CardType.HEART),
            new Card(CardName.SIX, CardType.DIAMOND)
    );

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

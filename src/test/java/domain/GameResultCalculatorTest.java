package domain;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultCalculatorTest {
    public static Stream<Arguments> getGameResultParameters() {
        return Stream.of(
                Arguments.of(
                        new Gamer("게이머1", HoldingCards.of(
                                new Card(CardName.SEVEN, CardType.HEART)
                        )),
                        new Gamer("게이머2", HoldingCards.of(
                                new Card(CardName.SIX, CardType.HEART)
                        )),
                        GameResult.WIN
                ),
                Arguments.of(
                        new Gamer("게이머1", HoldingCards.of(
                                new Card(CardName.SIX, CardType.HEART)
                        )),
                        new Gamer("게이머2", HoldingCards.of(
                                new Card(CardName.SEVEN, CardType.HEART)
                        )),
                        GameResult.LOSE
                ),
                Arguments.of(
                        new Gamer("게이머1", HoldingCards.of(
                                new Card(CardName.SEVEN, CardType.HEART)
                        )),
                        new Gamer("게이머2", HoldingCards.of(
                                new Card(CardName.SEVEN, CardType.DIAMOND)
                        )),
                        GameResult.TIE
                )
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

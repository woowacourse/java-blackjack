package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.player.User;
import blackjack.domain.result.Result;
import blackjack.money.BettingMoney;
import blackjack.money.MoneyCalculator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MoneyCalculatorTest {

    private static List<Card> createFirstReceivedCard(final CardNumber firstCardNumber, final CardNumber secondCardNumber) {
        return List.of(new Card(CardPattern.DIAMOND, firstCardNumber), new Card(CardPattern.HEART, secondCardNumber));
    }

    @ParameterizedTest
    @MethodSource("provideUserAndResultAndBettingMoney")
    @DisplayName("승무패에 따른 수익을 계산한다.")
    void calculateTest(final User user, final Result result, final BettingMoney bettingMoney, final int expected) {
        MoneyCalculator moneyCalculator = new MoneyCalculator();

        final int actual = moneyCalculator.calculate(user, result, bettingMoney);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideUserAndResultAndBettingMoney() {
        return Stream.of(
                Arguments.of(new User("pobi",createFirstReceivedCard(CardNumber.ACE, CardNumber.JACK)), Result.WIN, BettingMoney.of(10000), 15000),
                Arguments.of(new User("poa",createFirstReceivedCard(CardNumber.KING, CardNumber.JACK)), Result.WIN, BettingMoney.of(10000), 10000),
                Arguments.of(new User("jun", createFirstReceivedCard(CardNumber.KING, CardNumber.NINE)), Result.DRAW, BettingMoney.of(10000), 0),
                Arguments.of(new User("renno", createFirstReceivedCard(CardNumber.TWO, CardNumber.JACK)), Result.LOSE, BettingMoney.of(10000), -10000)
        );
    }
}

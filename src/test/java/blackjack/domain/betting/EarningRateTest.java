package blackjack.domain.betting;

import blackjack.domain.card.Card;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.card.painting.Suit.*;
import static blackjack.domain.card.painting.Symbol.*;
import static blackjack.domain.scoreboard.WinOrLose.*;
import static org.assertj.core.api.Assertions.assertThat;

class EarningRateTest {
    @DisplayName("승패와 상태를 기반으로 상금을 계산한다")
    @ParameterizedTest
    @MethodSource
    void calculatePrize(List<Card> cards, WinOrLose winOrLose, long expectedMoney) {
        //given
        long bettingMoneyAmount = 1000L;
        User user = new User("웨지", bettingMoneyAmount);
        user.drawCards(cards);
        //when
        BettingMoney resultMoney = EarningRate.calculate(user, winOrLose);
        //then
        assertThat(resultMoney).isEqualTo(new BettingMoney(expectedMoney));
    }

    private static Stream<Arguments> calculatePrize() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(CLOVER, JACK), new Card(DIAMOND, FOUR),
                        new Card(HEART, ACE)), WIN, 2000),
                Arguments.of(Arrays.asList(
                        new Card(DIAMOND, ACE), new Card(DIAMOND, KING)), WIN, 2500),
                Arguments.of(Arrays.asList(
                        new Card(DIAMOND, ACE), new Card(DIAMOND, KING)), DRAW, 1000),
                Arguments.of(Arrays.asList(
                        new Card(DIAMOND, FOUR), new Card(DIAMOND, THREE)), LOSE, 0)
        );
    }
}
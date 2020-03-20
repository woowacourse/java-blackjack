package domain.result;

import domain.user.Money;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ResultTest {
    @ParameterizedTest
    @MethodSource({"getCasesForJudge"})
    void judge(Result expected) {
        MatchRule mockMatchRule = mock(MatchRule.class);
        Player mockPlayer = mock(Player.class);
        Dealer mockDealer = mock(Dealer.class);
        when(mockMatchRule.match(mockPlayer, mockDealer)).thenReturn(expected);

        Result actual = Result.judge(mockMatchRule, mockPlayer, mockDealer);
        assertThat(actual).isEqualTo(expected);
        verify(mockMatchRule).match(mockPlayer, mockDealer);
    }

    private static Stream<Arguments> getCasesForJudge() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN_WITH_BLACKJACK),
                Arguments.of(Result.PLAYER_WIN_WITHOUT_BLACKJACk),
                Arguments.of(Result.DEALER_WIN),
                Arguments.of(Result.DRAW)
        );
    }

    @ParameterizedTest
    @MethodSource({"getCasesForTesingCalculateProfit"})
    @DisplayName("#calculateProfit() : should return profit")
    void calculateProfit(Result result) {
        Money money = mock(Money.class);
        when(money.multiply(anyDouble())).thenReturn(money);

        Profit profit = result.calculateProfit(money);
        assertThat(profit).isEqualTo(new Profit(money));

    }

    private static Stream<Arguments> getCasesForTesingCalculateProfit() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN_WITHOUT_BLACKJACk),
                Arguments.of(Result.PLAYER_WIN_WITH_BLACKJACK),
                Arguments.of(Result.DRAW),
                Arguments.of(Result.DEALER_WIN)
        );
    }
}
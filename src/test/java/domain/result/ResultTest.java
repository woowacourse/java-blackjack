package domain.result;

import domain.money.Money;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ResultTest {

    @ParameterizedTest
    @MethodSource({"getCasesForTestingJudge"})
    @DisplayName("#judge() : should return Result")
    void judge(Player player, Dealer dealer, Result expected) {
        Result actual = Result.judge(player, dealer);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource({"getCasesForTesingCalculateProfit"})
    @DisplayName("#calculateProfit() : should return profit")
    void calculateProfit(Result result) {
        Money money = mock(Money.class);

        //when
        result.calculateProfit(money);
        verify(money).multiply(anyDouble());
    }

    private static Stream<Arguments> getCasesForTestingJudge() {
        int scoreOfPlayer = 15;
        Player mockPlayer = stubPlayer(scoreOfPlayer);
        Dealer mockDealer = stubDealer(scoreOfPlayer);

        return Stream.of(
                Arguments.of(mockPlayer, mockDealer, Result.DEALER_WIN),
                Arguments.of(mockPlayer, mockDealer, Result.PLAYER_WIN_WITH_BLACKJACK),
                Arguments.of(mockPlayer, mockDealer, Result.PLAYER_WIN_WITHOUT_BLACKJACk),
                Arguments.of(mockPlayer, mockDealer, Result.DRAW),
                Arguments.of(mockPlayer, mockDealer, Result.PLAYER_WIN_WITH_BLACKJACK),
                Arguments.of(mockPlayer, mockDealer, Result.DEALER_WIN),
                Arguments.of(mockPlayer, mockDealer, Result.DRAW),
                Arguments.of(mockPlayer, mockDealer, Result.PLAYER_WIN_WITHOUT_BLACKJACk)
        );
    }

    //todo: 가독성 올리기
    private static Player stubPlayer(int scoreOfPlayer) {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.isBust())
                .thenReturn(true)
                .thenReturn(false);

        when(mockPlayer.isBlackjack())
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        when(mockPlayer.calculateScore()).thenReturn(scoreOfPlayer);
        return mockPlayer;
    }

    //todo: 가독성 올리기
    private static Dealer stubDealer(int scoreOfPlayer) {
        Dealer mockDealer = mock(Dealer.class);
        when(mockDealer.isBust())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(mockDealer.isBlackjack())
                .thenReturn(true)
                .thenReturn(false);
        when(mockDealer.isNotBlackjack()).thenReturn(true);
        when(mockDealer.calculateScore())
                .thenReturn(scoreOfPlayer + 1)
                .thenReturn(scoreOfPlayer)
                .thenReturn(scoreOfPlayer - 1);
        return mockDealer;
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
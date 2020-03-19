package domain.result;

import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultMatchRuleTest {
    private DefaultMatchRule defaultMatchRule;

    @BeforeEach
    void setUp() {
        defaultMatchRule = new DefaultMatchRule();
    }

    @ParameterizedTest
    @DisplayName("#match() : should return Result.DEALER_WIN")
    @MethodSource({"getCasesForDealerWin"})
    void matchDealerWin(Player player, Dealer dealer) {
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.DEALER_WIN);
    }

    @ParameterizedTest
    @DisplayName("#match() : should return Result.PLAYER_WIN_WITH_BLACKJACK")
    @MethodSource({"getCasesForPlayerWinWithBlackjack"})
    void matchPlayerWinWithBlackjack(Player player, Dealer dealer) {
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.PLAYER_WIN_WITH_BLACKJACK);
    }

    @ParameterizedTest
    @DisplayName("#match() : should return Result.PLAYER_WIN_WITHOUT_BLACKJACK")
    @MethodSource({"getCasesForPlayerWinWithoutBlackjack"})
    void matchPlayerWinWWithoutBlackjack(Player player, Dealer dealer) {
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.PLAYER_WIN_WITHOUT_BLACKJACk);
    }

    @ParameterizedTest
    @DisplayName("#match() : should return Result.DRAW")
    @MethodSource({"getCasesForDraw"})
    void matchDraw(Player player, Dealer dealer) {
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.DRAW);
    }

    private static Stream<Arguments> getCasesForDraw() {
        int score = 15;
        Player mockPlayer = stubPlayerForDraw(score);
        Dealer mockDealer = stubDealerForDraw(score);
        return Stream.of(
                Arguments.of(mockPlayer, mockDealer),
                Arguments.of(mockPlayer, mockDealer)
        );
    }

    private static Dealer stubDealerForDraw(int score) {
        Dealer mockDealer = mock(Dealer.class);
        when(mockDealer.isBlackjack())
                .thenReturn(true)
                .thenReturn(false);
        when(mockDealer.isBust())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false);
        when(mockDealer.isNotBlackjack()).thenReturn(true);
        when(mockDealer.calculateScore()).thenReturn(score);
        return mockDealer;
    }

    private static Player stubPlayerForDraw(int score) {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.isBust())
                .thenReturn(false)
                .thenReturn(false);
        when((mockPlayer.isBlackjack()))
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false);
        when(mockPlayer.isNotBlackjack())
                .thenReturn(true)
                .thenReturn(true);
        when(mockPlayer.calculateScore()).thenReturn(score);
        return mockPlayer;
    }

    private static Stream<Arguments> getCasesForPlayerWinWithoutBlackjack() {
        int scoreOfPlayer = 15;
        Player mockPlayer = stubPlayerForPlayerWinWithoutBlackjack(scoreOfPlayer);
        Dealer mockDealer = stubDealerForPlayerWinoutBlackjack(scoreOfPlayer);
        return Stream.of(
                Arguments.of(mockPlayer, mockDealer),
                Arguments.of(mockPlayer, mockDealer)
        );
    }

    private static Dealer stubDealerForPlayerWinoutBlackjack(int scoreOfPlayer) {
        Dealer mockDealer = mock(Dealer.class);
        when(mockDealer.isBust())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(false);
        when(mockDealer.isBlackjack())
                .thenReturn(false)
                .thenReturn(false);
        when(mockDealer.calculateScore()).thenReturn(scoreOfPlayer - 1);
        return mockDealer;
    }

    private static Player stubPlayerForPlayerWinWithoutBlackjack(int scoreOfPlayer) {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.isBust())
                .thenReturn(false)
                .thenReturn(false);
        when(mockPlayer.isBlackjack())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false);
        when(mockPlayer.isNotBlackjack())
                .thenReturn(true)
                .thenReturn(false);
        when(mockPlayer.calculateScore()).thenReturn(scoreOfPlayer);
        return mockPlayer;
    }

    private static Stream<Arguments> getCasesForPlayerWinWithBlackjack() {
        Player mockPlayer = stubPlayerForPlayerWinWithBlackjack();
        Dealer mockDealer = stubDealerForPlayerWinWithBlackjack();
        return Stream.of(
                Arguments.of(mockPlayer, mockDealer),
                Arguments.of(mockPlayer, mockDealer)
        );
    }

    private static Dealer stubDealerForPlayerWinWithBlackjack() {
        Dealer mockDealer = mock(Dealer.class);
        when(mockDealer.isBust())
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(false);

        when(mockDealer.isBlackjack()).thenReturn(false);
        when(mockDealer.isNotBlackjack()).thenReturn(true);
        return mockDealer;
    }

    private static Player stubPlayerForPlayerWinWithBlackjack() {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.isBust())
                .thenReturn(false)
                .thenReturn(false);

        when(mockPlayer.isBlackjack())
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(true);

        when(mockPlayer.isNotBlackjack()).thenReturn(false);
        return mockPlayer;
    }

    private static Stream<Arguments> getCasesForDealerWin() {
        int scoreOfPlayer = 15;
        Player mockPlayer = stubPlayerForDealerWin(scoreOfPlayer);
        Dealer mockDealer = stubDealerForDealerWin(scoreOfPlayer);

        return Stream.of(
                Arguments.of(mockPlayer, mockDealer),
                Arguments.of(mockPlayer, mockDealer)
        );

    }

    private static Dealer stubDealerForDealerWin(int scoreOfPlayer) {
        Dealer mockDealer = mock(Dealer.class);
        when(mockDealer.isBust())
                .thenReturn(false);
        when(mockDealer.isBlackjack()).thenReturn(false);
        when(mockDealer.isNotBlackjack()).thenReturn(false);

        when(mockDealer.calculateScore()).thenReturn(scoreOfPlayer + 1);
        return mockDealer;
    }

    private static Player stubPlayerForDealerWin(int scoreOfPlayer) {
        Player mockPlayer = mock(Player.class);

        when(mockPlayer.isBust())
                .thenReturn(true)
                .thenReturn(false);

        when(mockPlayer.isBlackjack())
                .thenReturn(false)
                .thenReturn(false);

        when(mockPlayer.calculateScore()).thenReturn(scoreOfPlayer);
        return mockPlayer;
    }
}
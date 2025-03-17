package blackjack.domain;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.money.BlackjackBettingMoney;
import blackjack.domain.money.Money;
import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BettingBoardTest {
    
    @Test
    void 플레이어는_금액을_배팅할_수_있다() {
        // given
        BettingBoard bettingBoard = new BettingBoard();
        
        // expected
        assertDoesNotThrow(() -> bettingBoard.bet(DEFAULT_PLAYER, new BlackjackBettingMoney(10000)));
    }
    
    @ParameterizedTest
    @MethodSource("provideWinningStatusAndProfit")
    void 블랙잭_결과로_달라지는_플레이어의_수익을_확인할_수_있다(WinningStatus winningStatus, Money profit) {
        // given
        BettingBoard bettingBoard = new BettingBoard();
        bettingBoard.bet(DEFAULT_PLAYER, new BlackjackBettingMoney(10000));
        
        // expected
        assertThat(bettingBoard.getProfit(DEFAULT_PLAYER, winningStatus))
                .isEqualTo(profit);
    }
    
    private static Stream<Arguments> provideWinningStatusAndProfit() {
        return Stream.of(
                Arguments.of(WinningStatus.BLACKJACK_WIN, new Money(15000)),
                Arguments.of(WinningStatus.WIN, new Money(10000)),
                Arguments.of(WinningStatus.DRAW, new Money(0)),
                Arguments.of(WinningStatus.LOSE, new Money(-10000))
        );
    }
    
    @ParameterizedTest
    @MethodSource("providePlayersWinningStatusAndProfit")
    void 모든_플레이어의_수익을_계산한_후_딜러의_총_수익을_계산할_수_있다(WinningStatus firstPlayerStatus,
                                               WinningStatus secondPlayerStatus,
                                               int profit
    ) {
        // given
        Player firstPlayer = new Player("돔푸");
        Player secondPlayer = new Player("메이");
        BettingBoard bettingBoard = new BettingBoard();
        bettingBoard.bet(firstPlayer, new BlackjackBettingMoney(10000));
        bettingBoard.bet(secondPlayer, new BlackjackBettingMoney(20000));
        
        // when
        Map<Player, WinningStatus> playerWinningStatus = new HashMap<>();
        playerWinningStatus.put(firstPlayer, firstPlayerStatus);
        playerWinningStatus.put(secondPlayer, secondPlayerStatus);
        
        // then
        assertThat(bettingBoard.getDealerProfit(playerWinningStatus)).isEqualTo(profit);
    }
    
    private static Stream<Arguments> providePlayersWinningStatusAndProfit() {
        return Stream.of(
                Arguments.of(WinningStatus.LOSE, WinningStatus.LOSE, 30000),
                Arguments.of(WinningStatus.BLACKJACK_WIN, WinningStatus.LOSE, 5000),
                Arguments.of(WinningStatus.BLACKJACK_WIN, WinningStatus.BLACKJACK_WIN, -45000),
                Arguments.of(WinningStatus.WIN, WinningStatus.WIN, -30000)
        );
    }
}

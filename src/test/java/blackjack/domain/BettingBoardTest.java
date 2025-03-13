package blackjack.domain;

import blackjack.domain.money.BettingMoney;
import blackjack.domain.player.Player;

import blackjack.test_util.TestConstants;

import java.util.HashMap;

import java.util.Map;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BettingBoardTest {
    
    @ParameterizedTest
    @MethodSource("provideWinningStatusAndProfit")
    void 블랙잭_결과로_달라지는_플레이어의_수익을_확인할_수_있다(WinningStatus winningStatus, int profit) {
        // given
        Map<Player, BettingMoney> playerBettingMoney = new HashMap<>();
        playerBettingMoney.put(TestConstants.DEFAULT_PLAYER, new BettingMoney(10000));
        
        // when
        BettingBoard bettingBoard = new BettingBoard(playerBettingMoney);
        
        // expected
        Assertions.assertThat(bettingBoard.getProfit(TestConstants.DEFAULT_PLAYER, winningStatus))
                .isEqualTo(profit);
    }
    
    private static Stream<Arguments> provideWinningStatusAndProfit() {
        return Stream.of(
                Arguments.of(WinningStatus.BLACKJACK_WIN, 15000),
                Arguments.of(WinningStatus.WIN, 10000),
                Arguments.of(WinningStatus.DRAW, 0),
                Arguments.of(WinningStatus.LOSE, -10000)
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
        Map<Player, BettingMoney> playerBettingMoney = new HashMap<>();
        playerBettingMoney.put(firstPlayer, new BettingMoney(10000));
        playerBettingMoney.put(secondPlayer, new BettingMoney(20000));
        BettingBoard bettingBoard = new BettingBoard(playerBettingMoney);
        
        // when
        Map<Player, WinningStatus> playerWinningStatus = new HashMap<>();
        playerWinningStatus.put(firstPlayer, WinningStatus.LOSE);
        playerWinningStatus.put(secondPlayer, WinningStatus.LOSE);
        
        // then
        Assertions.assertThat(bettingBoard.getDealerProfit(playerWinningStatus)).isEqualTo(30000);
    }
    
    @Test
    void 모든_플레이어의_수익을_계산한_후_딜러의_총_수익을_계산할_수_있다_2() {
        // given
        Player firstPlayer = new Player("돔푸");
        Player secondPlayer = new Player("메이");
        Map<Player, BettingMoney> playerBettingMoney = new HashMap<>();
        playerBettingMoney.put(firstPlayer, new BettingMoney(10000));
        playerBettingMoney.put(secondPlayer, new BettingMoney(20000));
        BettingBoard bettingBoard = new BettingBoard(playerBettingMoney);
        
        // when
        Map<Player, WinningStatus> playerWinningStatus = new HashMap<>();
        playerWinningStatus.put(firstPlayer, WinningStatus.BLACKJACK_WIN);
        playerWinningStatus.put(secondPlayer, WinningStatus.LOSE);
        
        // then
        Assertions.assertThat(bettingBoard.getDealerProfit(playerWinningStatus)).isEqualTo(5000);
    }
}

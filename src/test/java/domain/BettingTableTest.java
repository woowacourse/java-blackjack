package domain;

import domain.participant.WinStatus;
import domain.participant.player.Player;
import domain.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BettingTableTest {
    private BettingTable bettingTable;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        bettingTable = new BettingTable();
        mockPlayer = mock(Player.class);
    }

    @Test
    @DisplayName("블랙잭으로 승리하면 판돈의 1.5배가 수익이 된다")
    void 블랙잭_승리_판돈_테스트() {
        // given
        Money initialBet = new Money("10000");
        bettingTable.placeBet(mockPlayer, initialBet);

        when(mockPlayer.getWinStatus()).thenReturn(WinStatus.WIN);
        when(mockPlayer.isBlackjack()).thenReturn(true);

        // when
        Money profit = bettingTable.calculateProfit(mockPlayer);

        // then
        assertThat(profit.toLong()).isEqualTo(15000L);
    }

    @Test
    void 패배_판돈_마이너스_테스트() {
        // given
        bettingTable.placeBet(mockPlayer, new Money("10000"));
        when(mockPlayer.getWinStatus()).thenReturn(WinStatus.LOSS);

        // when
        Money profit = bettingTable.calculateProfit(mockPlayer);

        // then
        assertThat(profit.toLong()).isEqualTo(-10000L);
    }

    @Test
    void 무승부_판돈_테스트() {
        // given
        bettingTable.placeBet(mockPlayer, new Money("10000"));
        when(mockPlayer.getWinStatus()).thenReturn(WinStatus.DRAW);

        // when
        Money profit = bettingTable.calculateProfit(mockPlayer);

        // then
        assertThat(profit.toLong()).isEqualTo(0L);
    }

    @Test
    @DisplayName("딜러의 총 수익은 플레이어들의 수익 합계의 반전이다")
    void 딜러_총수익_계산_테스트() {
        // given
        Player player2 = mock(Player.class);
        bettingTable.placeBet(mockPlayer, new Money("10000"));
        bettingTable.placeBet(player2, new Money("20000"));

        when(mockPlayer.getWinStatus()).thenReturn(WinStatus.LOSS);
        when(player2.getWinStatus()).thenReturn(WinStatus.WIN);
        when(player2.isBlackjack()).thenReturn(true);

        bettingTable.calculateProfit(mockPlayer);
        bettingTable.calculateProfit(player2);

        // when
        Money dealerProfit = bettingTable.getDealerProfit();

        // then
        assertThat(dealerProfit.toLong()).isEqualTo(-20000L);
    }
}
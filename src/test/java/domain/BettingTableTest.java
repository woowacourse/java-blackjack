package domain;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BettingTableTest {

    private Map<Player, Money> createMoneyTable(Player... players) {
        Map<Player, Money> moneyTable = new LinkedHashMap<>();
        for (Player player : players) {
            moneyTable.put(player, new Money(1000L));
        }
        return moneyTable;
    }

    @Test
    void 승리시_베팅금액은_1배로_정산된다() {
        // given
        Player player1 = new Player("시오");
        Player player2 = new Player("봉구스");
        BettingTable bettingTable = new BettingTable(createMoneyTable(player1,player2));

        // when
        bettingTable.settleBet(player1, WinningStatus.WIN);
        bettingTable.settleBet(player2, WinningStatus.WIN);

        // then
        assertEquals(1000L, bettingTable.getPlayerMoneyAmount(player1));
        assertEquals(1000L, bettingTable.getPlayerMoneyAmount(player2));
    }

    @Test
    void 패배시_베팅금액은_1배로_차감된다() {
        // given
        Player player1 = new Player("시오");
        BettingTable bettingTable = new BettingTable(createMoneyTable(player1));

        // when
        bettingTable.settleBet(player1, WinningStatus.LOSE);

        // then
        assertEquals(-1000L, bettingTable.getPlayerMoneyAmount(player1));
    }

    @Test
    void 블랙잭일_때_베팅금액은_일점오배로_정산된다() {
        // given
        Player player1 = new Player("시오");
        BettingTable bettingTable = new BettingTable(createMoneyTable(player1));

        // when
        bettingTable.settleBet(player1, WinningStatus.BLACKJACK_WIN);

        // then
        assertEquals(1500L, bettingTable.getPlayerMoneyAmount(player1));
    }

    @Test
    void 승리한_플레이어들의_베팅금액만큼_딜러수익은_차감된다() {
        // given
        Player player1 = new Player("시오");
        Player player2 = new Player("봉구스");
        BettingTable bettingTable = new BettingTable(createMoneyTable(player1,player2));

        // when
        bettingTable.settleBet(player1, WinningStatus.WIN);
        bettingTable.settleBet(player2, WinningStatus.WIN);

        // then
        assertEquals(-2000L, bettingTable.calculateDealerProfit());
    }

    @Test
    void 패배한_플레이어들의_베팅금액만큼_딜러수익은_증가한다() {
        // given
        Player player1 = new Player("시오");
        Player player2 = new Player("봉구스");
        BettingTable bettingTable = new BettingTable(createMoneyTable(player1,player2));

        // when
        bettingTable.settleBet(player1, WinningStatus.LOSE);
        bettingTable.settleBet(player2, WinningStatus.LOSE);

        // then
        assertEquals(2000L, bettingTable.calculateDealerProfit());
    }

    @Test
    void 블랙잭_으로_승리한_플레이어들의_베팅금액만큼_딜러수익은_차감된다() {
        // given
        Player player1 = new Player("시오");
        Player player2 = new Player("봉구스");
        BettingTable bettingTable = new BettingTable(createMoneyTable(player1,player2));

        // when
        bettingTable.settleBet(player1, WinningStatus.BLACKJACK_WIN);
        bettingTable.settleBet(player2, WinningStatus.BLACKJACK_WIN);

        // then
        assertEquals(-3000L, bettingTable.calculateDealerProfit());
    }
}
package domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BettingTableTest {

    @Test
    void 승리시_베팅금액은_1배로_정산된다() {
        // given
        Player player1 = new Player(new Name("시오"));
        Player player2 = new Player(new Name("봉구스"));
        Map<Player, Money> bets = Map.of(
                player1, new Money(1000L),
                player2, new Money(2000L)
        );
        BettingTable bettingTable = new BettingTable(bets);

        // when
        bettingTable.settleBet(player1, WinningStatus.WIN);
        bettingTable.settleBet(player2, WinningStatus.WIN);

        // then
        assertEquals(1000L, bettingTable.getPlayerMoneyAmount(player1));
        assertEquals(2000L, bettingTable.getPlayerMoneyAmount(player2));
    }

    @Test
    void 패배시_베팅금액은_1배로_차감된다() {
        // given
        Player player1 = new Player(new Name("시오"));
        Map<Player, Money> bets = Map.of(
                player1, new Money(1000L)
        );
        BettingTable bettingTable = new BettingTable(bets);

        // when
        bettingTable.settleBet(player1, WinningStatus.LOSE);

        // then
        assertEquals(-1000L, bettingTable.getPlayerMoneyAmount(player1));
    }

    @Test
    void 블랙잭일_때_베팅금액은_일점오배로_정산된다() {
        // given
        Player player1 = new Player(new Name("시오"));
        Map<Player, Money> bets = Map.of(
                player1, new Money(1000L)
        );
        BettingTable bettingTable = new BettingTable(bets);

        // when
        bettingTable.settleBet(player1, WinningStatus.BLACKJACK_WIN);

        // then
        assertEquals(1500L, bettingTable.getPlayerMoneyAmount(player1));
    }

    @Test
    void 승리한_플레이어들의_베팅금액만큼_딜러수익은_차감된다() {
        // given
        Player player1 = new Player(new Name("시오"));
        Player player2 = new Player(new Name("봉구스"));
        Map<Player, Money> bets = Map.of(
                player1, new Money(1000L),
                player2, new Money(2000L)
        );
        BettingTable bettingTable = new BettingTable(bets);

        // when
        bettingTable.settleBet(player1, WinningStatus.WIN);
        bettingTable.settleBet(player2, WinningStatus.WIN);

        // then
        assertEquals(-3000L, bettingTable.calculateDealerProfit());
    }

    @Test
    void 패배한_플레이어들의_베팅금액만큼_딜러수익은_증가한다() {
        // given
        Player player1 = new Player(new Name("시오"));
        Player player2 = new Player(new Name("봉구스"));
        Map<Player, Money> bets = Map.of(
                player1, new Money(2000L),
                player2, new Money(3000L)
        );
        BettingTable bettingTable = new BettingTable(bets);

        // when
        bettingTable.settleBet(player1, WinningStatus.LOSE);
        bettingTable.settleBet(player2, WinningStatus.LOSE);

        // then
        assertEquals(5000L, bettingTable.calculateDealerProfit());
    }

    @Test
    void 블랙잭으로_승리한_플레이어들의_베팅금액만큼_딜러수익은_차감된다() {
        // given
        Player player1 = new Player(new Name("시오"));
        Player player2 = new Player(new Name("봉구스"));
        Map<Player, Money> bets = Map.of(
                player1, new Money(1000L),
                player2, new Money(2000L)
        );
        BettingTable bettingTable = new BettingTable(bets);

        // when
        bettingTable.settleBet(player1, WinningStatus.BLACKJACK_WIN);
        bettingTable.settleBet(player2, WinningStatus.BLACKJACK_WIN);

        // then
        assertEquals(-4500L, bettingTable.calculateDealerProfit());
    }
}
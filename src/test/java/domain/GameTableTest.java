package domain;

import domain.card.Card;
import domain.table.GameTable;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTableTest {

    private final GameTable gameTable;
    private final String playerName = "포비";
    private final String dealerName = "딜러";

    public GameTableTest() {
        this.gameTable = new GameTable(Map.of(playerName, 1000));
    }

    @DisplayName("카드의 총합이 21보다 크면 CurrentResult의 isBust는 true이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf22_returnTrue() {
        gameTable.draw(playerName, new Card("10","클로버"));
        gameTable.draw(playerName, new Card("5", "하트"));
        gameTable.draw(playerName, new Card("7", "하트"));

        Assertions.assertTrue(gameTable.checkBust("포비"));
    }

    @DisplayName("카드의 총합이 21보다 작으면 CurrentResult의 isBust는 false이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf20_returnFalse() {
        gameTable.draw(playerName, new Card("10","클로버"));
        gameTable.draw(playerName, new Card("5", "하트"));
        gameTable.draw(playerName, new Card("5", "스페이드"));

        Assertions.assertFalse(gameTable.checkBust("포비"));
    }

    @DisplayName("딜러의 카드 총합이 21을 넘으면 버스트(true)이다.")
    @Test
    void dealerTest_dealerTotalValueUpperThan21_returnTrue() {
        gameTable.draw(dealerName, new Card("10", "하트"));
        gameTable.draw(dealerName, new Card("10", "스페이드"));
        gameTable.draw(dealerName, new Card("5", "클로버"));

        Assertions.assertTrue(gameTable.checkBust(dealerName));
    }

    @DisplayName("딜러와 플레이어가 둘 다 블랙잭이면 둘 다 수익이 0이다.")
    @Test
    void blackjackTest_dealerAndPlayerBlackjack_return0() {
        gameTable.draw(dealerName, new Card("10", "하트"));
        gameTable.draw(dealerName, new Card("A", "스페이드"));

        gameTable.draw(playerName, new Card("10","클로버"));
        gameTable.draw(playerName, new Card("A", "하트"));

        gameTable.applyBlackjackBonus(playerName);
        Map<String, Integer> gameResult = gameTable.checkGameResult();
        int playerAmount = gameResult.get(playerName);

        Assertions.assertEquals(0, playerAmount);
    }

    @DisplayName("플레이어가 블랙잭이고, 딜러는 블랙잭이 아니면 플레이어의 수익은 1.5배다.")
    @Test
    void blackjackTest_playerIsBlackjack_1_5() {
        gameTable.draw(dealerName, new Card("10", "하트"));
        gameTable.draw(dealerName, new Card("8", "스페이드"));

        gameTable.draw(playerName, new Card("10","클로버"));
        gameTable.draw(playerName, new Card("A", "하트"));

        gameTable.applyBlackjackBonus(playerName);
        Map<String, Integer> gameResult = gameTable.checkGameResult();
        int playerAmount = gameResult.get(playerName);

        Assertions.assertEquals(1500, playerAmount);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이 아니고, 딜러가 18, 플레이어가 16이면 딜러가 이긴다.")
    @Test
    void blackjackTest_playerAndDealerIsNotBlackjack_1() {
        gameTable.draw(dealerName, new Card("10", "하트"));
        gameTable.draw(dealerName, new Card("8", "스페이드"));

        gameTable.draw(playerName, new Card("10","클로버"));
        gameTable.draw(playerName, new Card("6", "하트"));

        Map<String, Integer> gameResult = gameTable.checkGameResult();
        int playerAmount = gameResult.get(playerName);

        Assertions.assertEquals(-1000, playerAmount);
    }
}
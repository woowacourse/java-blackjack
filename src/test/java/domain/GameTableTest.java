package domain;

import domain.card.Card;
import domain.table.GameTable;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTableTest {

    private final GameTable gameTable;
    private final String playerName = "포비";

    public GameTableTest() {
        this.gameTable = new GameTable(List.of(playerName));
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
}
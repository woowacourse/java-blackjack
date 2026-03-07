package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTableTest {

    private final GameTable gameTable;

    public GameTableTest() {
        this.gameTable = new GameTable();
    }

    @DisplayName("카드의 총합이 21보다 크면 CurrentResult의 isBust는 true이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf22_returnTrue() {
        Member pobi = new Member("포비", Role.PLAYER);

        gameTable.joinMember(pobi);

        gameTable.draw(pobi.name(), new Card("10","클로버"));
        gameTable.draw(pobi.name(), new Card("5", "하트"));
        gameTable.draw(pobi.name(), new Card("7", "하트"));

        Assertions.assertTrue(gameTable.checkBust("포비"));
    }

    @DisplayName("카드의 총합이 21보다 작으면 CurrentResult의 isBust는 false이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf20_returnFalse() {
        Member pobi = new Member("포비", Role.PLAYER);

        gameTable.joinMember(pobi);

        gameTable.draw(pobi.name(), new Card("10","클로버"));
        gameTable.draw(pobi.name(), new Card("5", "하트"));
        gameTable.draw(pobi.name(), new Card("5", "스페이드"));

        Assertions.assertFalse(gameTable.checkBust("포비"));
    }
}
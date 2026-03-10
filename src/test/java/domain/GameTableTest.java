package domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTableTest {

    private static final String pobiName = "pobi";

    private GameTable gameTable;

    @BeforeEach
    void setUpTest() {
        List<Card> cards = List.of(
                new Card("10", "클로버"),
                new Card("9", "클로버"),
                new Card("8", "클로버"),
                new Card("7", "클로버")
        );
        this.gameTable = new GameTable(List.of(pobiName), new FixedDeck(cards));
    }

    @DisplayName("카드의 총합이 21보다 크면 CurrentResult의 isBust는 true이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf22_returnTrue() {
        gameTable.drawForMember(pobiName);
        gameTable.drawForMember(pobiName);
        gameTable.drawForMember(pobiName);

        Assertions.assertTrue(gameTable.checkBust(pobiName));
    }

    @DisplayName("카드의 총합이 21보다 작으면 CurrentResult의 isBust는 false이다.")
    @Test
    void checkCurrentTest_playerHasCardSumOf20_returnFalse() {
        gameTable.drawForMember(pobiName);
        gameTable.drawForMember(pobiName);

        Assertions.assertFalse(gameTable.checkBust(pobiName));
    }
}

package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameManagerTest {
    @Test
    @DisplayName("플레이어들의 승패 결과를 딜러의 점수와 비교하여 정확히 Map으로 반환한다.")
    void getGameResult_ReturnMap() {
        Player pobi = new Player(new Name("pobi"));
        Player crong = new Player(new Name("crong"));
        GameManager gameManager = new GameManager(List.of(pobi, crong));

        Dealer dealer = gameManager.getDealer();

        dealer.receiveCard(new Card(Shape.SPADE, Number.TEN));
        dealer.receiveCard(new Card(Shape.HEART, Number.EIGHT));

        pobi.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        pobi.receiveCard(new Card(Shape.CLUB, Number.TEN));

        crong.receiveCard(new Card(Shape.SPADE, Number.NINE));
        crong.receiveCard(new Card(Shape.HEART, Number.SEVEN));

        Map<String, Boolean> result = gameManager.getGameResult();

        boolean pobiResult = result.get("pobi");
        boolean crongResult = result.get("crong");

        assertEquals(true, pobiResult);
        assertEquals(false, crongResult);
    }
}

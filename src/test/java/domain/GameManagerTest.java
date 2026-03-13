package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
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

        Map<String, GameResult> result = gameManager.getGameResult();

        GameResult pobiResult = result.get("pobi");
        GameResult crongResult = result.get("crong");

        assertEquals(GameResult.WIN, pobiResult);
        assertEquals(GameResult.LOSE, crongResult);
    }

//    @Test
//    @DisplayName("플레이어들의 이름에 중복이 있는지 검사한다.")
//    void validateDuplicateName_DuplicateName_ThrowsException() {
//        List<String> name = new ArrayList<>();
//        name.add("pobi");
//        name.add("jason");
//        name.add("pobi");
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            GameManager.validatePlayersNumber(name);
//        });
//        assertEquals("중복된 참가자 이름이 있습니다!", exception.getMessage());
//    }
}

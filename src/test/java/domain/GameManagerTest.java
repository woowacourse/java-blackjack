package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class GameManagerTest {
    @Test
    void 플레이어들의_점수를_딜러와_비교해_승패를_반환한다() {
        Player pobi = new Player(new Name("pobi"));
        Player crong = new Player(new Name("crong"));
        Players players = new Players(List.of(pobi, crong));
        GameManager gameManager = new GameManager(players);

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
}

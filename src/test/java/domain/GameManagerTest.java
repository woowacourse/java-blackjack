package domain;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.game.GameManager;
import domain.game.GameResult;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class GameManagerTest {
    @Test
    void 플레이어들의_점수를_딜러와_비교해_승패를_반환한다() {
        // TODO: 단위 테스트라기보다 얇은 통합 테스트 느낌 추후 리팩토링 예정
        Player pobi = new Player(new Name("pobi"));
        Player crong = new Player(new Name("crong"));
        Players players = new Players(List.of(pobi, crong));
        GameManager gameManager = new GameManager(players);

        Dealer dealer = gameManager.getDealer();

        dealer.receiveCard(new Card(Shape.SPADE, domain.card.Number.TEN));
        dealer.receiveCard(new Card(Shape.HEART, domain.card.Number.EIGHT));

        pobi.receiveCard(new Card(Shape.DIAMOND, domain.card.Number.TEN));
        pobi.receiveCard(new Card(Shape.CLUB, domain.card.Number.TEN));

        crong.receiveCard(new Card(Shape.SPADE, domain.card.Number.NINE));
        crong.receiveCard(new Card(Shape.HEART, Number.SEVEN));

        Map<String, GameResult> result = gameManager.getGameResult();

        GameResult pobiResult = result.get("pobi");
        GameResult crongResult = result.get("crong");

        assertEquals(GameResult.WIN, pobiResult);
        assertEquals(GameResult.LOSE, crongResult);
    }
}

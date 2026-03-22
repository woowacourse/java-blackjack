package domain.game;

import static org.junit.jupiter.api.Assertions.*;

import domain.betting.BettingAmount;
import domain.betting.Revenue;
import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class GameResultManagerTest {
    @Test
    void 플레이어들의_점수를_딜러와_비교해_승패를_반환한다() {
        TestFixture fixture = createFixture();
        dealCards(fixture.gameManager(), fixture.pobi(), fixture.crong());

        Map<String, GameResult> result = fixture.gameManager().getGameResult();

        assertEquals(GameResult.WIN, result.get("pobi"));
        assertEquals(GameResult.LOSE, result.get("crong"));
    }

    @Test
    void 참가자별_수익은_딜러를_먼저_포함해_반환한다() {
        TestFixture fixture = createFixture();
        dealCards(fixture.gameManager(), fixture.pobi(), fixture.crong());

        Map<Name, Revenue> profits = fixture.gameManager().getParticipantsProfit();

        assertEquals(List.of("딜러", "pobi", "crong"),
                profits.keySet().stream().map(Name::getName).toList());
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(profits.get(new Name("딜러")).getMoney()));
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(profits.get(new Name("pobi")).getMoney()));
        assertEquals(0, BigDecimal.valueOf(-2000).compareTo(profits.get(new Name("crong")).getMoney()));
    }

    private TestFixture createFixture() {
        Player pobi = new Player(new Name("pobi"), new BettingAmount(BigDecimal.valueOf(1000)));
        Player crong = new Player(new Name("crong"), new BettingAmount(BigDecimal.valueOf(2000)));
        Players players = new Players(List.of(pobi, crong));
        return new TestFixture(new GameManager(players), pobi, crong);
    }

    private record TestFixture(GameManager gameManager, Player pobi, Player crong) {
    }

    private static void dealCards(GameManager gameManager, Player pobi, Player crong) {
        gameManager.getDealer().receiveCard(new Card(Shape.SPADE, Number.TEN));
        gameManager.getDealer().receiveCard(new Card(Shape.HEART, Number.EIGHT));

        pobi.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        pobi.receiveCard(new Card(Shape.CLUB, Number.TEN));

        crong.receiveCard(new Card(Shape.SPADE, Number.NINE));
        crong.receiveCard(new Card(Shape.HEART, Number.SEVEN));
    }
}

package domain.game;

import static org.junit.jupiter.api.Assertions.*;

import domain.betting.BettingAmount;
import domain.betting.BettingAmounts;
import domain.betting.CalculateProfit;
import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class GameResultManagerTest {
    @Test
    void 플레이어들의_점수를_딜러와_비교해_승패를_반환한다() {
        TestFixture fixture = createFixture();
        dealCards(fixture.dealer(), fixture.pobi(), fixture.crong());

        GameResultManager gameResultManager = createGameResultManager(fixture);

        Map<String, GameResult> result = gameResultManager.getGameResult();

        assertEquals(GameResult.WIN, result.get("pobi"));
        assertEquals(GameResult.LOSE, result.get("crong"));
    }

    private GameResultManager createGameResultManager(TestFixture fixture) {
        BettingAmounts bettingBook = fixture.players().createBettingAmounts(BigDecimal.valueOf(1000));
        CalculateProfit calculateProfit = new CalculateProfit(bettingBook);
        return new GameResultManager(calculateProfit, fixture.players(), fixture.dealer());
    }

    private TestFixture createFixture() {
        Player pobi = new Player(new Name("pobi"));
        Player crong = new Player(new Name("crong"));
        Players players = new Players(List.of(pobi, crong));
        Dealer dealer = new Dealer();
        return new TestFixture(pobi, crong, players, dealer);
    }

    private record TestFixture(Player pobi, Player crong, Players players, Dealer dealer) {
    }

    private static void dealCards(Dealer dealer, Player pobi, Player crong) {
        dealer.receiveCard(new Card(Shape.SPADE, Number.TEN));
        dealer.receiveCard(new Card(Shape.HEART, Number.EIGHT));

        pobi.receiveCard(new Card(Shape.DIAMOND, Number.TEN));
        pobi.receiveCard(new Card(Shape.CLUB, Number.TEN));

        crong.receiveCard(new Card(Shape.SPADE, Number.NINE));
        crong.receiveCard(new Card(Shape.HEART, Number.SEVEN));
    }
}

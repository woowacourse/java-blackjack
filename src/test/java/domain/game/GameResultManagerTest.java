package domain.game;

import static org.junit.jupiter.api.Assertions.*;

import domain.betting.BettingAmount;
import domain.betting.BettingAmounts;
import domain.betting.CalculateProfit;
import domain.card.Help;
import domain.card.Number;
import domain.card.Shape;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class GameResultManagerTest {
    @Test
    void 플레이어들의_점수를_딜러와_비교해_승패를_반환한다() {
        Player pobi = new Player(new Name("pobi"));
        Player crong = new Player(new Name("crong"));
        Players players = new Players(List.of(pobi, crong));
        GameManager gameManager = new GameManager(players);
        Dealer dealer = gameManager.getDealer();

        dealer.receiveCard(new Help(Shape.SPADE, domain.card.Number.TEN));
        dealer.receiveCard(new Help(Shape.HEART, domain.card.Number.EIGHT));

        pobi.receiveCard(new Help(Shape.DIAMOND, domain.card.Number.TEN));
        pobi.receiveCard(new Help(Shape.CLUB, domain.card.Number.TEN));

        crong.receiveCard(new Help(Shape.SPADE, domain.card.Number.NINE));
        crong.receiveCard(new Help(Shape.HEART, Number.SEVEN));

        Map<Name, BettingAmount> bettingAmounts = new HashMap<>();
        players.forEach(player -> bettingAmounts.put(player.getName(), new BettingAmount(1000)));

        BettingAmounts bettingManager = new BettingAmounts(bettingAmounts);
        CalculateProfit calculateProfit = new CalculateProfit(bettingManager);
        GameResultManager gameResultManager = new GameResultManager(calculateProfit, players, dealer);

        Map<String, GameResult> result = gameResultManager.getGameResult();

        assertEquals(GameResult.WIN, result.get("pobi"));
        assertEquals(GameResult.LOSE, result.get("crong"));
    }
}

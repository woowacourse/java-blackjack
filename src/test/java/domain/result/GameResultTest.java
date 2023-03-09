package domain.result;

import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {

    GameResult gameResult;

    @BeforeEach
    void setup() {
        Players players = new Players(List.of("hardy", "ddoring"));
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        // 2장씩 뽑는다.
        dealer.hit(deck.popCard());
        dealer.hit(deck.popCard());
        players.hitTwice(deck);

        /*
            딜러: K, Q
            hardy: J 10
            ddoring: 9 8
         */
        gameResult = new GameResult(dealer, players);
    }

    @Test
    void makeDealerResultTest() {
        EnumMap<Outcome, Integer> dealerResult = gameResult.makeDealerResult();

        assertEquals(1, dealerResult.get(Outcome.WIN));
        assertEquals(1, dealerResult.get(Outcome.DRAW));
        assertEquals(0, dealerResult.get(Outcome.LOSE));
    }

    @Test
    void makePlayerResultsTest() {
        Map<String, Outcome> playerResults = gameResult.makePlayerResults();

        assertEquals(Outcome.DRAW, playerResults.get("hardy"));
        assertEquals(Outcome.LOSE, playerResults.get("ddoring"));
    }
}

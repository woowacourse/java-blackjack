package domain.result;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Suit;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameResultTest {

    GameResult gameResult;
    Players players;
    Dealer dealer;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
        players = new Players(List.of("hardy", "ddoring", "crong", "doodoom"));
        // 2장씩 뽑는다.

        // 19
        dealer.hit(Card.of(Suit.HEART, Rank.NINE));
        dealer.hit(Card.of(Suit.DIAMOND, Rank.TEN));

        // 21 블랙잭으로 이김
        Player hardy = players.findPlayer("hardy");
        hardy.hit(Card.of(Suit.DIAMOND, Rank.ACE));
        hardy.hit(Card.of(Suit.DIAMOND, Rank.TEN));

        // 20 그냥 이김
        Player ddoring = players.findPlayer("ddoring");
        ddoring.hit(Card.of(Suit.DIAMOND, Rank.KING));
        ddoring.hit(Card.of(Suit.DIAMOND, Rank.KING));

        // 19 무승부
        Player crong = players.findPlayer("crong");
        crong.hit(Card.of(Suit.DIAMOND, Rank.NINE));
        crong.hit(Card.of(Suit.DIAMOND, Rank.KING));

        // 18 짐
        Player doodoom = players.findPlayer("doodoom");
        doodoom.hit(Card.of(Suit.DIAMOND, Rank.EIGHT));
        doodoom.hit(Card.of(Suit.DIAMOND, Rank.KING));

        gameResult = new GameResult(dealer, players);
    }

    @DisplayName("플레이어는 블랙잭 점수에 따라 WIN, DRAW, LOSE의 게임결과를 저장한다.")
    @Test
    void makePlayerResultsTest() {
        assertEquals(Outcome.WIN, gameResult.getOutcomeByPlayer(players.findPlayer("hardy")));
        assertEquals(Outcome.DRAW, gameResult.getOutcomeByPlayer(players.findPlayer("crong")));
        assertEquals(Outcome.LOSE, gameResult.getOutcomeByPlayer(players.findPlayer("doodoom")));
    }

    @DisplayName("플레이어는 게임 결과에 따라 수익을 저장한다.")
    @Test
    void makePlayerBetMoneyResultsTest() {
        players.getPlayers()
                .forEach(player -> player.initBet(10000));
        Map<String, Integer> playerBetMoneyResults = gameResult.makePlayerBetMoneyResults(players);

        Integer hardyMoney = playerBetMoneyResults.get("hardy");
        Integer ddoringMoney = playerBetMoneyResults.get("ddoring");
        Integer crongMoney = playerBetMoneyResults.get("crong");
        Integer doodoomMoney = playerBetMoneyResults.get("doodoom");

        // 블랙잭으로 이김
        assertEquals(15000, hardyMoney);

        // 그냥 이김
        assertEquals(10000, ddoringMoney);

        // 무승부
        assertEquals(0, crongMoney);

        // 짐
        assertEquals(-10000, doodoomMoney);
    }

    @DisplayName("딜러의 최종 수익은 플레이어의 최종 수익의 합의 * (-1)이다.")
    @Test
    void calculateDealerBetMoneyResultTest() {
        int expectedDealerProfit = -15000;
        players.getPlayers()
                .forEach(player -> player.initBet(10000));
        Map<String, Integer> playerBetMoneyResults = gameResult.makePlayerBetMoneyResults(players);
        int dealerProfit = gameResult.calculateDealerBetMoneyResult(playerBetMoneyResults);

        assertEquals(expectedDealerProfit, dealerProfit);
    }
}

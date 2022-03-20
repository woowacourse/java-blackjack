package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackProfitResultTest {

    private final Player player1 = new Player("kei");
    private final Player player2 = new Player("rookie");
    private final Player player3 = new Player("parang");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("딜러 수익 테스트")
    void calculateDealerProfit() {
        game();

        BlackjackProfitResult blackjackProfitResult = new BlackjackProfitResult(initializePlayers());
        Map<Player, Double> playersInfo = blackjackProfitResult.calculatePlayersProfit(initializeResult());
        Double dealerProfit = blackjackProfitResult.calculateDealerProfit(playersInfo);

        assertThat(dealerProfit).isEqualTo(-17500.0);
    }

    @Test
    @DisplayName("플레이어 수익 테스트")
    void calculatePlayersProfit() {
        game();

        BlackjackProfitResult blackjackProfitResult = new BlackjackProfitResult(initializePlayers());
        Map<Player, Double> playersInfo = blackjackProfitResult.calculatePlayersProfit(initializeResult());

        assertThat(playersInfo).containsExactly(
                Map.entry(player1, -10000.0), Map.entry(player2, 7500.0), Map.entry(player3, 20000.0)
        );
    }

    private Map<Player, BettingMoney> initializePlayers() {
        final Map<Player, BettingMoney> players = new LinkedHashMap<>();
        players.put(player1, new BettingMoney(10000));
        players.put(player2, new BettingMoney(5000));
        players.put(player3, new BettingMoney(20000));
        return players;
    }

    private void game() {
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        dealer.receiveCard(Card.from(Suit.CLOVER, Denomination.TWO));
        dealer.requestStay();

        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player1.receiveCard(Card.from(Suit.HEART, Denomination.TWO));
        player1.requestStay();

        player2.receiveCard(Card.from(Suit.DIAMOND, Denomination.ACE));
        player2.receiveCard(Card.from(Suit.DIAMOND, Denomination.KING));

        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.TWO));
        player3.requestStay();
    }

    private Map<Player, BlackjackMatch> initializeResult() {
        final Map<Player, BlackjackMatch> result = new LinkedHashMap<>();
        result.put(player1, BlackjackMatch.LOSE);
        result.put(player2, BlackjackMatch.WIN);
        result.put(player3, BlackjackMatch.WIN);
        return result;
    }
}
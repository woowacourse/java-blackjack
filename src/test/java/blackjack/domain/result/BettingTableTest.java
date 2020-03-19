package blackjack.domain.result;

import blackjack.controller.dto.GamersResultDto;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BettingTableTest {

    Dealer dealer = new Dealer();

    @BeforeEach
    void setUp() {
        setDealerCard();
    }

    @Test
    @DisplayName("블랙잭 Winner 수익 계산")
    void calculateProfitBlackJackWinner() {
        Players players = Players.of(Arrays.asList("blackJackWinner"));
        Player blackJackWinner = players.findPlayer("blackJackWinner");
        Map<String, String> playerBettingMoney = new HashMap<>();
        playerBettingMoney.put("blackJackWinner", "10000");

        blackJackWinner.draw(new Card(CardSymbol.ACE, CardType.CLOVER));
        blackJackWinner.draw(new Card(CardSymbol.KING, CardType.CLOVER));

        BettingTable bettingTable = BettingTable.of(players, playerBettingMoney);

        assertThat(bettingTable.calculateProfit(dealer, players).getPlayersResult()).containsEntry(blackJackWinner, 15000);
    }

    @Test
    @DisplayName("Winner 수익 계산")
    void calculateProfitWinner() {
        Players players = Players.of(Arrays.asList("winner"));
        Player blackJackWinner = players.findPlayer("winner");
        Map<String, String> playerBettingMoney = new HashMap<>();
        playerBettingMoney.put("winner", "10000");

        blackJackWinner.draw(new Card(CardSymbol.NINE, CardType.CLOVER));
        blackJackWinner.draw(new Card(CardSymbol.KING, CardType.CLOVER));

        BettingTable bettingTable = BettingTable.of(players, playerBettingMoney);

        assertThat(bettingTable.calculateProfit(dealer, players).getPlayersResult()).containsEntry(blackJackWinner, 10000);
    }

    @Test
    @DisplayName("Drawer 수익 계산")
    void calculateProfitDrawer() {
        Players players = Players.of(Arrays.asList("drawer"));
        Player blackJackWinner = players.findPlayer("drawer");
        Map<String, String> playerBettingMoney = new HashMap<>();
        playerBettingMoney.put("drawer", "5000");

        blackJackWinner.draw(new Card(CardSymbol.ACE, CardType.CLOVER));
        blackJackWinner.draw(new Card(CardSymbol.KING, CardType.CLOVER));

        BettingTable bettingTable = BettingTable.of(players, playerBettingMoney);

        assertThat(bettingTable.calculateProfit(dealer, players).getPlayersResult()).containsEntry(blackJackWinner, 0);
    }

    @Test
    @DisplayName("Loser 수익 계산")
    void calculateProfitLoser() {
        Players players = Players.of(Arrays.asList("loser"));
        Player blackJackWinner = players.findPlayer("loser");
        Map<String, String> playerBettingMoney = new HashMap<>();
        playerBettingMoney.put("loser", "10000");

        blackJackWinner.draw(new Card(CardSymbol.ACE, CardType.CLOVER));
        blackJackWinner.draw(new Card(CardSymbol.KING, CardType.CLOVER));

        BettingTable bettingTable = BettingTable.of(players, playerBettingMoney);

        assertThat(bettingTable.calculateProfit(dealer, players).getPlayersResult()).containsEntry(blackJackWinner, -10000);
    }

    private void setDealerCard() {
        dealer.draw(new Card(CardSymbol.FIVE, CardType.CLOVER));
        dealer.draw(new Card(CardSymbol.KING, CardType.DIAMOND));
    }


}
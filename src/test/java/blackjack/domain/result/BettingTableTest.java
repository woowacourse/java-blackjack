package blackjack.domain.result;

import blackjack.controller.dto.PlayersBettingMoneyDto;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Profit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BettingTableTest {

    Dealer dealer = new Dealer();
    Map<String, String> nameBettingMoney;

    @BeforeEach
    void setUp() {
        setDealerCard();
        nameBettingMoney = new HashMap<>();
    }

    @Test
    @DisplayName("블랙잭 Winner 수익 계산")
    void calculateProfitBlackJackWinner() {
        nameBettingMoney.put("blackJackWinner", "10000");
        PlayersBettingMoneyDto dto = new PlayersBettingMoneyDto(nameBettingMoney);
        Players players = Players.of(dto);
        Player blackJackWinner = players.findPlayer("blackJackWinner");

        blackJackWinner.draw(new Card(CardSymbol.ACE, CardType.CLOVER));
        blackJackWinner.draw(new Card(CardSymbol.KING, CardType.CLOVER));

        assertThat(new BettingTable().calculateProfit(dealer, players).getGamersResult()).containsEntry(blackJackWinner, new Profit(15000))
                .containsEntry(dealer, new Profit(-15000));
    }

    @Test
    @DisplayName("Winner 수익 계산")
    void calculateProfitWinner() {
        nameBettingMoney.put("winner", "10000");
        PlayersBettingMoneyDto dto = new PlayersBettingMoneyDto(nameBettingMoney);
        Players players = Players.of(dto);
        Player winner = players.findPlayer("winner");

        winner.draw(new Card(CardSymbol.NINE, CardType.CLOVER));
        winner.draw(new Card(CardSymbol.KING, CardType.CLOVER));

        assertThat(new BettingTable().calculateProfit(dealer, players).getGamersResult()).containsEntry(winner, new Profit(10000))
                .containsEntry(dealer, new Profit(-10000));
    }

    @Test
    @DisplayName("Drawer 수익 계산")
    void calculateProfitDrawer() {
        nameBettingMoney.put("drawer", "10000");
        PlayersBettingMoneyDto dto = new PlayersBettingMoneyDto(nameBettingMoney);
        Players players = Players.of(dto);
        Player drawer = players.findPlayer("drawer");

        drawer.draw(new Card(CardSymbol.FIVE, CardType.CLOVER));
        drawer.draw(new Card(CardSymbol.KING, CardType.CLOVER));

        assertThat(new BettingTable().calculateProfit(dealer, players).getGamersResult()).containsEntry(drawer, new Profit(0))
                .containsEntry(dealer, new Profit(0));
    }

    @Test
    @DisplayName("Loser 수익 계산")
    void calculateProfitLoser() {
        nameBettingMoney.put("loser", "10000");
        PlayersBettingMoneyDto dto = new PlayersBettingMoneyDto(nameBettingMoney);
        Players players = Players.of(dto);
        Player loser = players.findPlayer("loser");

        loser.draw(new Card(CardSymbol.THREE, CardType.CLOVER));
        loser.draw(new Card(CardSymbol.KING, CardType.CLOVER));

        assertThat(new BettingTable().calculateProfit(dealer, players).getGamersResult()).containsEntry(loser, new Profit(-10000))
                .containsEntry(dealer, new Profit(10000));
    }

    private void setDealerCard() {
        dealer.draw(new Card(CardSymbol.FIVE, CardType.CLOVER));
        dealer.draw(new Card(CardSymbol.KING, CardType.DIAMOND));
    }

}
package domain;

import domain.card.Card;
import domain.card.Number;
import domain.deck.TotalDeck;
import domain.game.Game;
import domain.game.Result;
import domain.money.Money;
import domain.money.MoneyManager;
import domain.money.Profit;
import domain.user.Player;
import domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static domain.card.Number.EIGHT;
import static domain.card.Number.NINE;
import static domain.card.Shape.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

public class MoneyManagerTest {
    @ParameterizedTest
    @CsvSource(value = {"ACE,KING,1500", "ACE,TWO,-1000", "ACE,NINE,1000", "EIGHT,NINE,0"})
    @DisplayName("게임 결과에 맞는 수익을 계산한다.")
    void calculatePlayerProfitTest(Number number1, Number number2, String profit) {
        Users users = new Users(List.of("a"));
        Player player = users.getPlayers().get(0);
        Game game = new Game(new TotalDeck(List.of(new Card(CLOVER, number1),
                new Card(CLOVER, number2),
                new Card(CLOVER, EIGHT),
                new Card(CLOVER, NINE)
        )), users);

        Map<Player, Result> playerResults = game.generatePlayerResults();
        Map<Player, Money> bettingManager = Map.of(player, new Money("1000"));
        MoneyManager moneyManager = new MoneyManager(bettingManager);
        Map<Player, Profit> profitManager = moneyManager.calculateProfit(playerResults);

        assertThat(profitManager.get(player).getValue().compareTo(new BigDecimal(profit))).isEqualTo(0);

    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,KING,-1500", "ACE,TWO,1000", "ACE,NINE,-1000", "EIGHT,NINE,0"})
    @DisplayName("딜러의 수익을 계산한다.")
    void calculateDealerProfitTest(Number number1, Number number2, String profitOfDealer) {
        Users users = new Users(List.of("a"));
        Player player = users.getPlayers().get(0);
        Game game = new Game(new TotalDeck(List.of(new Card(CLOVER, number1),
                new Card(CLOVER, number2),
                new Card(CLOVER, EIGHT),
                new Card(CLOVER, NINE)
        )), users);

        Map<Player, Result> playerResults = game.generatePlayerResults();
        Map<Player, Money> bettingManager = Map.of(player, new Money("1000"));
        MoneyManager moneyManager = new MoneyManager(bettingManager);
        Profit profit = moneyManager.makeDealerProfit(playerResults);

        assertThat(profit.getValue().compareTo(new BigDecimal(profitOfDealer))).isEqualTo(0);
    }
}

package domain;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.deck.TotalDeck;
import domain.game.Game;
import domain.game.Result;
import domain.user.Player;
import domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyManagerTest {
    @Test
    @DisplayName("게임 결과에 맞는 수익을 계산한다.")
    void calculateProfitTest() {
        Users users = new Users(List.of("a"));
        Player player = users.getPlayers().get(0);
        Game game = new Game(new TotalDeck(List.of(new Card(Shape.CLOVER, Number.SIX),
                new Card(Shape.CLOVER, Number.SEVEN),
                new Card(Shape.CLOVER, Number.EIGHT),
                new Card(Shape.CLOVER, Number.NINE)
        )), users);

        Map<Player, Result> playerResults = game.generatePlayerResults();
        Map<Player, Money> bettingManager = Map.of(player, new Money("1000"));
        MoneyManager moneyManager = new MoneyManager(bettingManager);
        Map<Player, Profit> profitManager = moneyManager.calculateProfit(playerResults);

        assertThat(profitManager.get(player).getValue()).isEqualTo(-1000);
    }
}

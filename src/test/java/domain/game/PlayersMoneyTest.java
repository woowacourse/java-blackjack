package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.user.Name;
import domain.user.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayersMoneyTest {
    @Test
    @DisplayName("블랙잭인 플레이어의 돈을 바꾼다.")
    void changeIfBlackjackTest() {
        Player player = new Player(new Name("aa"));
        PlayersMoney playersMoney = new PlayersMoney(new HashMap<>(Map.of(player, new Money(1000))));

        player.addCard(new Card(Shape.CLUB, Number.ACE));
        player.addCard(new Card(Shape.CLUB, Number.TEN));

        playersMoney.changeIfBlackjack();

        assertThat(playersMoney.getPlayersMoney().get(player).value()).isEqualTo(1500);
    }

    @Test
    @DisplayName("블랙잭이지 않은 플레이어의 돈을 바꾸지 않는다.")
    void NotChangeIfNotBlackjackTest() {
        Player player = new Player(new Name("aa"));
        PlayersMoney playersMoney = new PlayersMoney(new HashMap<>(Map.of(player, new Money(1000))));

        player.addCard(new Card(Shape.CLUB, Number.ACE));
        player.addCard(new Card(Shape.CLUB, Number.NINE));

        playersMoney.changeIfBlackjack();

        assertThat(playersMoney.getPlayersMoney().get(player).value()).isEqualTo(1000);
    }

    @ParameterizedTest(name = "{1}")
    @CsvSource(value = {"WIN,이긴 경우", "DRAW,비긴 경우", "LOSE,진 경우"})
    @DisplayName("플레이 결과에 따라 플레이어의 돈을 바꾼다.")
    void changeByPlayerResultsWinTest(GameResult gameResult, String whichCase) {
        Player player = new Player(new Name("aa"));
        Money money = new Money(1000);

        PlayersMoney playersMoney = new PlayersMoney(new HashMap<>(Map.of(player, money)));

        Map<Player, GameResult> playerResults = Map.of(player, gameResult);

        playersMoney.changeByPlayerResults(playerResults);

        assertThat(playersMoney.getPlayersMoney()).containsExactly(Map.entry(player, money.change(gameResult)));
    }

    @Test
    @DisplayName("딜러의 금액은 플레이어가 잃은 금액이다.")
    void calculateDealerMoneyTest() {
        Player player = new Player(new Name("aa"));
        PlayersMoney playersMoney = new PlayersMoney(new HashMap<>(Map.of(player, new Money(1000))));

        assertThat(playersMoney.calculateDealerMoney()).isEqualTo(-1000);
    }
}

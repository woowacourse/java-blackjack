package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.user.BetAmount;
import domain.user.Name;
import domain.user.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersMoneyTest {
    @Test
    @DisplayName("블랙잭인 플레이어의 돈을 바꾼다.")
    void changeIfBlackjackTest() {
        Player player = new Player(new Name("aa"));
        PlayersMoney playersMoney = new PlayersMoney(new HashMap<>(Map.of(player, new BetAmount(1000))));

        player.addCard(new Card(Shape.CLUB, Number.ACE));
        player.addCard(new Card(Shape.CLUB, Number.TEN));

        playersMoney.changeIfBlackjack();

        assertThat(playersMoney.getPlayersMoney().get(player).value()).isEqualTo(1500);
    }

    @Test
    @DisplayName("블랙잭이지 않은 플레이어의 돈을 바꾸지 않는다.")
    void NotChangeIfNotBlackjackTest() {
        Player player = new Player(new Name("aa"));
        PlayersMoney playersMoney = new PlayersMoney(new HashMap<>(Map.of(player, new BetAmount(1000))));

        player.addCard(new Card(Shape.CLUB, Number.ACE));
        player.addCard(new Card(Shape.CLUB, Number.NINE));

        playersMoney.changeIfBlackjack();

        assertThat(playersMoney.getPlayersMoney().get(player).value()).isEqualTo(1000);
    }
}

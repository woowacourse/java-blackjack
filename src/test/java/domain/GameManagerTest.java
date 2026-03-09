package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.player.Name;
import domain.player.Player;
import domain.player.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 딜러에게_2장의_카드를_지급한다() {
        GameManager manager = new GameManager(Deck.create());
        Dealer dealer = new Dealer();

        manager.dealCard(dealer);
        manager.dealCard(dealer);

        CardDto actual = manager.getCardsResult(dealer);

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void 플레이어에게_2장의_카드를_지급한다() {
        GameManager manager = new GameManager(Deck.create());
        Player player = new Player(Name.from("나무"));

        manager.dealCard(player);
        manager.dealCard(player);

        CardDto actual = manager.getCardsResult(player);

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void 전체_플레이어에게_2장의_카드를_지급한다() {
        GameManager manager = new GameManager(Deck.create());
        Player player1 = new Player(Name.from("나무"));
        Player player2 = new Player(Name.from("고래"));

        Players players = Players.of(List.of(player1, player2));

        for (Player player : players) {
            manager.dealCard(player);
            manager.dealCard(player);

            CardDto actual = manager.getCardsResult(player);

            assertThat(actual.size()).isEqualTo(2);
        }
    }
}

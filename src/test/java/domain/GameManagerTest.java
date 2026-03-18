package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardsSnapshot;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 딜러에게_2장의_카드를_지급한다() {
        GameManager manager = GameManager.createWith(Deck.create());
        Dealer dealer = new Dealer();

        manager.dealInitialCards(dealer);

        CardsSnapshot actual = manager.getCardsResult(dealer);

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void 플레이어에게_2장의_카드를_지급한다() {
        GameManager manager = GameManager.createWith(Deck.create());
        Player player = new Player(Name.from("나무"));

        manager.dealInitialCards(player);

        CardsSnapshot actual = manager.getCardsResult(player);

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void 전체_플레이어에게_2장의_카드를_지급한다() {
        GameManager manager = GameManager.createWith(Deck.create());
        Player player1 = new Player(Name.from("나무"));
        Player player2 = new Player(Name.from("고래"));

        Players players = Players.of(List.of(player1, player2));

        for (Player player : players) {
            manager.dealInitialCards(player);

            CardsSnapshot actual = manager.getCardsResult(player);

            assertThat(actual.size()).isEqualTo(2);
        }
    }
}

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameManagerTest {

    @Test
    void 등록된_플레이어와_딜러_순서대로_카드를_돌린다() {
        Player player1 = new Player("pobi");
        Player player2 = new Player("cary");

        Players players = new Players();
        players.add(player1);
        players.add(player2);
        List<Player> records = players.getPlayers();
        Dealer dealer = new Dealer();
        GameManager manager = new GameManager();

        manager.dealCards(records, dealer);

        assertThat(player1.getHand().size()).isEqualTo(2);
        assertThat(player2.getHand().size()).isEqualTo(2);
        assertThat(dealer.getHand().size()).isEqualTo(2);
    }
}
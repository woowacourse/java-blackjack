package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTableTest {

    @DisplayName("게임을 시작할 때 딜러와 플레이어들에게 카드를 2장씩 나눠준다.")
    @Test
    void firstDraw() {
        Dealer dealer = new Dealer();
        Players players = new Players(Arrays.asList("choonsik", "jason"));
        GameTable gameTable = new GameTable(dealer, players);
        gameTable.drawAtFirst();

        assertThat(dealer.getScore().toInt()).isBetween(2, 21);
        players.getPlayers()
            .forEach(player -> assertThat(player.getScore().toInt()).isBetween(2, 21));
    }
}
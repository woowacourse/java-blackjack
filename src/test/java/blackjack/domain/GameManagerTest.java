package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameManagerTest {

    @Test
    @DisplayName("참가자를 추가한다")
    void 참가자를_추가한다() {
        List<Player> names = List.of(new Player("비타"));

        GameManager gameManager = new GameManager();
        gameManager.addGamblers(names);

        assertThat(gameManager.getPlayers().getGamblers().size())
                .isEqualTo(2);
    }

    @DisplayName("참가자에게 카드를 한장 추가 발부한다")
    @Test
    void deal_card_to_gambler_test() {
        Player player = new Player("두리");

        GameManager gameManager = new GameManager();
        gameManager.addGamblers(List.of(player));

        gameManager.dealAddCard(player);
        assertThat(player.getCards().size()).isEqualTo(3);
    }
}

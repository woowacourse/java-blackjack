package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {


    @Test
    @DisplayName("카드를 2장씩 분배한다.")
    void test_deal_two_cards() {
        var players = List.of(
                new Player(),
                new Player()
        );
        var game = new Game(players, new Deck());

        game.dealCards();

        for (Player player : players) {
            assertThat(player.getCards()).hasSize(2);
        }
    }

}

package blackjack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    @Test
    void createGame() {
        List<String> inputs = Arrays.asList("wannte","bepoz");
        Game game = Game.of(inputs);
        assertThat(game.getPlayers().size()).isEqualTo(2);
    }
}
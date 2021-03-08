package blackjack.domain.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersTest {
    private Players players;

    @BeforeEach
    void setUp() {
        List<String> input = Arrays.asList("pobi", "jason");
        players = new Players(input, new Dealer());
    }

    @Test
    @DisplayName("카드 한장씩 분배 확인")
    void playerReceiveCards() {
        players.giveCards();
        assertEquals((int) players.getPlayers().stream().filter(player -> player.toList().size() == 1).count(), 2);
    }
}

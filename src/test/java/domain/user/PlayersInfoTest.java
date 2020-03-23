package domain.user;

import domain.TestUtils;
import domain.card.DeckFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersInfoTest {

    private PlayersInfo playersInfo;

    @BeforeEach
    void setUp() {
        playersInfo = TestUtils.createPlayersInfo(Arrays.asList("pobi", "jason", "woo"), 1000);
    }

    @Test
    @DisplayName("각 플레이어 draw")
    void draw() {
        List<Integer> expected = playersInfo.getPlayers()
                .stream()
                .map(player -> player.cards.getCards().size() + 1)
                .collect(Collectors.toList());

        playersInfo.draw(DeckFactory.createDeck());

        List<Integer> result = playersInfo.getPlayers()
                .stream()
                .map(player -> player.cards.getCards().size())
                .collect(Collectors.toList());

        assertThat(expected).isEqualTo(result);
    }
}
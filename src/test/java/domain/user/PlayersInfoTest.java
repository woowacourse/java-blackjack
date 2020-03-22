package domain.user;

import domain.card.Deck;
import domain.card.DeckFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersInfoTest {

    private PlayersInfo playersInfo;

    @Mock
    private Deck deck;

    @BeforeEach
    void setUp() {
        List<String> names = Arrays.asList("pobi", "jason", "woo");
        Map<String, Integer> playerInfo = names.stream()
                .collect(Collectors.toMap(Function.identity(), name -> 1000,
                        (e1, e2) -> {
                            throw new AssertionError("중복된 키가 있습니다.");
                        },
                        LinkedHashMap::new));
        playersInfo = PlayersInfo.of(playerInfo);
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
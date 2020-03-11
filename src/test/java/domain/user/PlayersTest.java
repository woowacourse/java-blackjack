package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.deck.DeckFactory;

class PlayersTest {
    private Players players;

    @BeforeEach
    void setUp() {
        String names = "pobi, jason";
        players = Players.of(names);
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi", "jason"})
    void create(String name) {
        List<String> playerNames = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        assertThat(playerNames).contains(name);
    }

    @Test
    void draw() {
        List<Integer> expected = players.getPlayers()
                .stream()
                .map(player -> player.cards.size() + 1)
                .collect(Collectors.toList());

        players.draw(DeckFactory.getDeck());

        List<Integer> result = players.getPlayers()
                .stream()
                .map(player -> player.cards.size())
                .collect(Collectors.toList());

        assertThat(expected).isEqualTo(result);
    }
}
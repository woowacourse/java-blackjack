package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.deck.Card;
import domain.deck.DeckFactory;
import domain.deck.Symbol;
import domain.deck.Type;

class PlayersTest {
    private Players players;

    @BeforeEach
    void setUp() {
        String names = "pobi, jason";
        players = Players.of(names);
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

    @ParameterizedTest
    @ValueSource(strings = {"pobi", "jason"})
    void getAllNames(String name) {
        String playerNames = players.getAllNames();

        assertThat(playerNames).contains(name);
    }

    @Test
    void getAllFirstDrawResult() {
        players.getPlayers()
                .forEach(player -> player.draw(new Card(Symbol.SPADE, Type.ACE)));
        String expected = "pobi카드: A스페이드\njason카드: A스페이드";

        assertThat(players.getAllFirstDrawResult()).isEqualTo(expected);
    }
}
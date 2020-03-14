package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.card.Card;
import domain.card.DeckFactory;
import domain.card.Symbol;
import domain.card.Type;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        String names = "pobi, jason, woo";
        players = Players.of(names);
    }

    @Test
    @DisplayName("각 플레이어 draw")
    void draw() {
        List<Integer> expected = players.getPlayers()
                .stream()
                .map(player -> player.cards.getCards().size() + 1)
                .collect(Collectors.toList());

        players.draw(DeckFactory.createDeck());

        List<Integer> result = players.getPlayers()
                .stream()
                .map(player -> player.cards.getCards().size())
                .collect(Collectors.toList());

        assertThat(expected).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("모든 플레이어 이름")
    @ValueSource(strings = {"pobi", "jason", "woo"})
    void getAllNames(String name) {
        String playerNames = players.getAllNames();

        assertThat(playerNames).contains(name);
    }

    @Test
    @DisplayName("모든 플레이어 첫 카드 분배 결과")
    void getAllFirstDrawResult() {
        players.getPlayers()
                .forEach(player -> player.draw(new Card(Symbol.SPADE, Type.ACE)));
        String expected = "pobi카드: A스페이드\njason카드: A스페이드\nwoo카드: A스페이드";

        assertThat(players.getAllFirstDrawResults()).isEqualTo(expected);
    }

    @Test
    @DisplayName("모든 플레이어 최종 결과")
    void getAllTotalDrawResult() {
        players.getPlayers()
                .get(0)
                .draw(new Card(Symbol.SPADE, Type.ACE));
        players.getPlayers()
                .get(1)
                .draw(new Card(Symbol.SPADE, Type.THREE));
        players.getPlayers()
                .get(1)
                .draw(new Card(Symbol.HEART, Type.FIVE));
        players.getPlayers()
                .get(2)
                .draw(new Card(Symbol.CLOVER, Type.KING));
        String expected = "pobi카드: A스페이드 - 결과: 11\n"
                + "jason카드: 3스페이드, 5하트 - 결과: 8\n"
                + "woo카드: K클로버 - 결과: 10";

        assertThat(players.getAllTotalDrawResults()).isEqualTo(expected);
    }
}
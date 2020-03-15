package domain.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import domain.card.Card;
import domain.card.Deck;
import domain.card.DeckFactory;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.ResultType;
import domain.rule.PlayerResultRule;

class PlayersTest {

    private Players players;

    @Mock
    private Deck deck;

    @BeforeEach
    void setUp() {
        List<String> names = Arrays.asList("pobi", "jason", "woo");
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
    @DisplayName("승자 결과")
    @MethodSource("createIndexAndResultType")
    void decideWinner(int index, ResultType expected) {
        MockitoAnnotations.initMocks(this);
        Queue<Card> cards = new LinkedList<>(Arrays.asList(
                new Card(Symbol.SPADE, Type.SIX),
                new Card(Symbol.SPADE, Type.SEVEN),
                new Card(Symbol.HEART, Type.FIVE),
                new Card(Symbol.CLOVER, Type.SIX))
        );
        Map<Player, ResultType> resultOfPlayers;

        given(deck.dealOut()).will(invocation -> cards.poll());

        Dealer dealer = Dealer.appoint();
        dealer.draw(deck);

        players = Players.of(Arrays.asList("pobi", "jason", "woo"));
        players.getPlayers()
                .get(0)
                .draw(deck);
        players.getPlayers()
                .get(1)
                .draw(deck);
        players.getPlayers()
                .get(2)
                .draw(deck);

        Rules rules = new Rules(Arrays.asList(PlayerResultRule.values()));

        resultOfPlayers = players.decideWinner(dealer, rules);
        Player player = players.getPlayers().get(index);

        assertThat(resultOfPlayers.get(player)).isEqualTo(expected);
    }

    private static Stream<Arguments> createIndexAndResultType() {
        return Stream.of(
                Arguments.of(0, ResultType.WIN),
                Arguments.of(1, ResultType.LOSE),
                Arguments.of(2, ResultType.DRAW)
        );
    }
}
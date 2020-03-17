package domain.result;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

class GameResultTest {

    private Players players;
    private Dealer dealer;

    @Mock
    private Deck deck;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        List<String> names = Arrays.asList("pobi", "jason", "woo");
        Map<String, Integer> playerInfo = names.stream()
                .collect(Collectors.toMap(Function.identity(), name -> 1000,
                        (e1, e2) -> {
                            throw new AssertionError("중복된 키가 있습니다.");
                        }, LinkedHashMap::new));
        players = Players.of(playerInfo);
        Queue<Card> cards = new LinkedList<>(Arrays.asList(
                new Card(Symbol.SPADE, Type.SIX),
                new Card(Symbol.SPADE, Type.SEVEN),
                new Card(Symbol.HEART, Type.FIVE),
                new Card(Symbol.CLOVER, Type.SIX))
        );
        given(deck.dealOut()).will(invocation -> cards.poll());

        dealer = Dealer.appoint();
        dealer.draw(deck);

        players.getPlayers()
                .get(0)
                .draw(deck);
        players.getPlayers()
                .get(1)
                .draw(deck);
        players.getPlayers()
                .get(2)
                .draw(deck);
    }

    @ParameterizedTest
    @DisplayName("플레이어들 게임 결과")
    @MethodSource("createIndexAndResultOfPlayers")
    void getResultOfPlayers(int index, ResultType expected) {
        Map<Player, ResultType> resultOfPlayers;
        resultOfPlayers = GameResult.of(dealer, players).getResultOfPlayers();
        Player player = players.getPlayers().get(index);

        assertThat(resultOfPlayers.get(player)).isEqualTo(expected);
    }

    private static Stream<Arguments> createIndexAndResultOfPlayers() {
        return Stream.of(
                Arguments.of(0, ResultType.WIN),
                Arguments.of(1, ResultType.LOSE),
                Arguments.of(2, ResultType.DRAW)
        );
    }

    @ParameterizedTest
    @DisplayName("플레이어들 게임 결과")
    @MethodSource("createIndexAndResultOfDealer")
    void getResultOfDealer(int index, ResultType expected) {
        Map<Player, ResultType> resultOfPlayers;
        resultOfPlayers = GameResult.of(dealer, players).getResultOfPlayers();
        Player player = players.getPlayers().get(index);

        assertThat(resultOfPlayers.get(player)).isEqualTo(expected);
    }

    private static Stream<Arguments> createIndexAndResultOfDealer() {
        return Stream.of(
                Arguments.of(0, ResultType.WIN),
                Arguments.of(1, ResultType.LOSE),
                Arguments.of(2, ResultType.DRAW)
        );
    }
}
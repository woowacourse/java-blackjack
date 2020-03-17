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
import domain.user.PlayersInfo;

class GameResultTest {

    private PlayersInfo playersInfo;
    private Dealer dealer;

    @Mock
    private Deck deck;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        List<String> names = Arrays.asList("pobi", "jason", "woo", "lowoon");
        Map<String, Integer> playerInfo = names.stream()
                .collect(Collectors.toMap(Function.identity(), name -> 1000,
                        (e1, e2) -> {
                            throw new AssertionError("중복된 키가 있습니다.");
                        }, LinkedHashMap::new));
        playersInfo = PlayersInfo.of(playerInfo);
        Queue<Card> cards = new LinkedList<>(Arrays.asList(
                new Card(Symbol.SPADE, Type.SIX),
                new Card(Symbol.SPADE, Type.SEVEN),
                new Card(Symbol.HEART, Type.FIVE),
                new Card(Symbol.CLOVER, Type.SIX),
                new Card(Symbol.SPADE, Type.ACE),
                new Card(Symbol.CLOVER, Type.KING)
        ));
        given(deck.dealOut()).will(invocation -> cards.poll());

        dealer = Dealer.appoint();
        dealer.draw(deck);

        playersInfo.getPlayers()
                .get(0)
                .draw(deck);
        playersInfo.getPlayers()
                .get(1)
                .draw(deck);
        playersInfo.getPlayers()
                .get(2)
                .draw(deck);
        playersInfo.getPlayers()
                .get(3)
                .draw(deck);
        playersInfo.getPlayers()
                .get(3)
                .draw(deck);
    }

    @ParameterizedTest
    @DisplayName("플레이어들 게임 결과")
    @MethodSource("createIndexAndProfitOfPlayers")
    void getProfitOfPlayers(int index, double expected) {
        Map<Player, Double> profitOfPlayers;
        profitOfPlayers = GameResult.of(dealer, playersInfo).getProfitOfPlayers();
        Player player = playersInfo.getPlayers().get(index);

        assertThat(profitOfPlayers.get(player)).isEqualTo(expected);
    }

    private static Stream<Arguments> createIndexAndProfitOfPlayers() {
        return Stream.of(
                Arguments.of(0, 1000),
                Arguments.of(1, -1000),
                Arguments.of(2, 0),
                Arguments.of(3, 1500)
        );
    }
}
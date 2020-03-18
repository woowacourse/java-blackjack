package domain.user;

import domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.OutputView;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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

    @Test
    @DisplayName("추가 드로우")
    void additionalDealOut() {
        MockitoAnnotations.initMocks(this);
        Queue<Card> cards = new LinkedList<>(Arrays.asList(
                new Card(Symbol.SPADE, Type.SIX),
                new Card(Symbol.SPADE, Type.SEVEN),
                new Card(Symbol.HEART, Type.FIVE),
                new Card(Symbol.CLOVER, Type.SIX))
        );
        List<Card> expected = new ArrayList<>(cards);

        given(deck.dealOut()).will(invocation -> cards.poll());

        PlayersInfo mockPlayersInfo = PlayersInfo.of(Collections.singletonMap("pobi", 1000));
        mockPlayersInfo.additionalDealOut(deck, (name) -> true, OutputView::printPlayerDealOutResult);

        List<Card> actual = mockPlayersInfo.getPlayers().get(0).getCards();

        assertThat(actual).containsAll(expected);
    }
}
package service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import domain.card.Card;
import domain.card.Deck;
import domain.card.DeckFactory;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayersInfo;
import domain.user.User;

class BlackJackGameTest {

    private Dealer dealer;
    private PlayersInfo playersInfo;

    @Mock
    private Deck deck;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        List<String> names = Arrays.asList("pobi", "jason", "woo", "lowoon");
        Map<String, Integer> players = names.stream()
                .collect(Collectors.toMap(Function.identity(), name -> 1000,
                        (e1, e2) -> {
                            throw new AssertionError("중복된 키가 있습니다.");
                        },
                        LinkedHashMap::new));
        playersInfo = PlayersInfo.of(players);
        Queue<Card> cards = new LinkedList<>(Arrays.asList(
                new Card(Symbol.SPADE, Type.SIX),
                new Card(Symbol.SPADE, Type.SEVEN),
                new Card(Symbol.HEART, Type.FIVE),
                new Card(Symbol.CLOVER, Type.SIX),
                new Card(Symbol.SPADE, Type.ACE)
        ));
        given(deck.dealOut()).will(invocation -> cards.poll());

        dealer = Dealer.appoint();
        dealer.draw(deck);
        playersInfo.draw(deck);

        given(deck.dealOut()).willReturn(new Card(Symbol.CLOVER, Type.KING));
        playersInfo.draw(deck);
        dealer.draw(deck);
    }

    @Test
    @DisplayName("게임 시작시 카드 분배")
    void firstDealOut() {
        Deck deck = DeckFactory.createDeck();
        int initSize = deck.getDeck().size();
        BlackJackGame.firstDealOut(deck, dealer, playersInfo);

        assertThat(deck.getDeck().size()).isEqualTo(initSize - 10);
    }

    @ParameterizedTest
    @DisplayName("모든 유저 카드 점수 결과")
    @CsvSource(value = {"0,16", "1,17", "2,15", "3,16", "4,21"})
    void getUserToCardPoint(int index, int expected) {
        List<Integer> usersCardPoint = new ArrayList<>(BlackJackGame.getUserToCardPoint(dealer, playersInfo)
                .values());

        assertThat(usersCardPoint.get(index)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어들 수익")
    @MethodSource("createIndexAndProfitOfPlayers")
    void getProfitOfPlayers(int index, int expected) {
        Map<Player, Integer> profitOfPlayers;
        profitOfPlayers = BlackJackGame.getProfitOfPlayers(dealer, playersInfo);
        int actual = new ArrayList<>(profitOfPlayers.values()).get(index);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> createIndexAndProfitOfPlayers() {
        return Stream.of(
                Arguments.of(0, 1000),
                Arguments.of(1, -1000),
                Arguments.of(2, 0),
                Arguments.of(3, 1500)
        );
    }

    @Test
    @DisplayName("딜러 최종 수익")
    void getProfitOfDealer() {
        assertThat(BlackJackGame.getProfitOfDealer(dealer, playersInfo)).isEqualTo(-1500);
    }
}
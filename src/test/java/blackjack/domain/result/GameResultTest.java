//package blackjack.domain.result;
//
//import blackjack.domain.playing.card.Card;
//import blackjack.domain.playing.card.Symbol;
//import blackjack.domain.playing.card.Type;
//import blackjack.domain.playing.deck.Deck;
//import blackjack.domain.playing.user.Dealer;
//import blackjack.domain.playing.user.Player;
//import blackjack.domain.playing.user.Players;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//class GameResultTest {
//    private Dealer dealer;
//    private Player playerToLose;
//    private Player playerToDraw;
//    private Player playerToWin;
//    private GameResult gameResult;
//
//    @Mock
//    private Deck deck;
//
//    @Mock
//    private Players players;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        dealer = Dealer.create();
//        playerToLose = Player.of("포비");
//        playerToDraw = Player.of("그니");
//        playerToWin = Player.of("무늬");
//
//        when(deck.draw()).thenReturn(new Card(Symbol.THREE, Type.DIAMOND));
//        dealer.drawCardsInTurn(deck);
//
//        when(deck.draw()).thenReturn(new Card(Symbol.TWO, Type.DIAMOND));
//        playerToLose.drawCardsInTurn(deck);
//
//        when(deck.draw()).thenReturn(new Card(Symbol.THREE, Type.DIAMOND));
//        playerToDraw.drawCardsInTurn(deck);
//
//        when(deck.draw()).thenReturn(new Card(Symbol.FOUR, Type.DIAMOND));
//        playerToWin.drawCardsInTurn(deck);
//
//        List<Player> playerList = Arrays.asList(playerToLose, playerToDraw, playerToWin);
//        when(players.getPlayers()).thenReturn(playerList);
//
//        gameResult = GameResult.of(dealer, players);
//    }
//
//    @Test
//    void of() {
//        assertThat(GameResult.of(dealer, players)).isNotNull();
//    }
//
//    @Test
//    void getPlayerResults() {
//        Map<Player, ResultType> expected = new LinkedHashMap<>();
//
//        expected.put(playerToLose, ResultType.LOSE);
//        expected.put(playerToDraw, ResultType.DRAW);
//        expected.put(playerToWin, ResultType.WIN);
//
//        assertThat(gameResult.getPlayerResults()).isEqualTo(expected);
//    }
//
//    @Test
//    void getDealerResults() {
//        Map<ResultType, Integer> expected = new LinkedHashMap<>();
//        expected.put(ResultType.WIN, 1);
//        expected.put(ResultType.DRAW, 1);
//        expected.put(ResultType.LOSE, 1);
//
//        assertThat(gameResult.getDealerResults()).isEqualTo(expected);
//    }
//}
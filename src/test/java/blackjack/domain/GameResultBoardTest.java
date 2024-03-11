package blackjack.domain;

import static blackjack.domain.card.Shape.CLOVER;
import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Shape.HEART;
import static blackjack.domain.card.Shape.SPADE;
import static blackjack.domain.card.Value.ACE;
import static blackjack.domain.card.Value.KING;
import static blackjack.domain.card.Value.NINE;
import static blackjack.domain.card.Value.QUEEN;
import static blackjack.domain.card.Value.TEN;
import static blackjack.domain.card.Value.THREE;
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultBoardTest {
    private static final int TEST_PLAYER_BET_AMOUNT = 10000;
    private static final List<Card> bustedCards = List.of(
            new Card(DIAMOND, THREE), new Card(DIAMOND, NINE), new Card(DIAMOND, KING)
    );

    static int calculateExpectedProfitOf(GameResult result) {
        return (int) (TEST_PLAYER_BET_AMOUNT * result.getProfitRate());
    }

    private Dealer generateBustedDealer() {
        Deck deck = new Deck(bustedCards);
        Dealer bustedDealer = new Dealer();
        giveCardToPlayer(bustedDealer.getPlayer(), deck, bustedCards.size());

        return bustedDealer;
    }

    private Player generateBustedPlayer() {
        Deck deck = new Deck(bustedCards);
        Player bustedPlayer = Player.from("testPlayer", TEST_PLAYER_BET_AMOUNT);
        giveCardToPlayer(bustedPlayer, deck, bustedCards.size());

        return bustedPlayer;
    }

    private void giveCardToPlayer(Player player, Deck deck, int drawAmount) {
        for (int i = 0; i < drawAmount; i++) {
            player.draw(deck);
        }
    }

    @ParameterizedTest
    @MethodSource("playerCardsAndExpectedProfitWhenNoOneBusted")
    @DisplayName("딜러와 플레이어 모두 버스트되지 않은 경우 각자의 점수에 따라 손익이 결정된다.")
    void noOneBustedTest(List<Card> playerCards, int expected) {
        List<Card> cards = new ArrayList<>(List.of(new Card(DIAMOND, KING), new Card(DIAMOND, NINE)));
        cards.addAll(playerCards);
        Deck deck = new Deck(cards);

        Dealer dealer = new Dealer();
        giveCardToPlayer(dealer.getPlayer(), deck, 2);
        Player player = Player.from("testPlayer", TEST_PLAYER_BET_AMOUNT);
        giveCardToPlayer(player, deck, 2);

        GameResultBoard gameResultBoard = new GameResultBoard(dealer, new Players(List.of(player)));

        assertThat(gameResultBoard.getGameResult(player)).isEqualTo(expected);
    }

    static Stream<Arguments> playerCardsAndExpectedProfitWhenNoOneBusted() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, ACE), new Card(DIAMOND, QUEEN)),
                        calculateExpectedProfitOf(GameResult.BLACKJACK)
                ),
                Arguments.arguments(
                        List.of(new Card(SPADE, TEN), new Card(CLOVER, TEN)),
                        calculateExpectedProfitOf(GameResult.WIN)
                ),
                Arguments.arguments(
                        List.of(new Card(HEART, QUEEN), new Card(CLOVER, NINE)),
                        calculateExpectedProfitOf(GameResult.DRAW)
                ),
                Arguments.arguments(
                        List.of(new Card(SPADE, ACE), new Card(HEART, ACE)),
                        calculateExpectedProfitOf(GameResult.LOSE)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("playerCardsAndExpectedProfitWhenDealerBusted")
    @DisplayName("딜러만 버스트된 경우 플레이어는 배팅한 금액(승리), 혹은 금액의 1.5배(블랙잭)를 얻는다.")
    void dealerBustedTest(List<Card> playerCards, int expected) {
        Deck deck = new Deck(playerCards);
        Dealer bustedDealer = generateBustedDealer();
        Player player = Player.from("testPlayer", TEST_PLAYER_BET_AMOUNT);
        giveCardToPlayer(player, deck, 2);

        GameResultBoard gameResultBoard = new GameResultBoard(bustedDealer, new Players(List.of(player)));

        assertThat(gameResultBoard.getGameResult(player)).isEqualTo(expected);
    }

    static Stream<Arguments> playerCardsAndExpectedProfitWhenDealerBusted() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, ACE), new Card(DIAMOND, QUEEN)),
                        calculateExpectedProfitOf(GameResult.BLACKJACK)
                ),
                Arguments.arguments(
                        List.of(new Card(SPADE, TWO), new Card(CLOVER, TWO)),
                        calculateExpectedProfitOf(GameResult.WIN)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("dealerCardsAndExpectedProfitWhenPlayerBusted")
    @DisplayName("플레이어가 버스트된 경우 플레이어는 딜러의 버스트 여부와 상관없이 배팅한 금액을 모두 잃는다.")
    void playerBustedTest(List<Card> dealerCards, int expected) {
        Deck deck = new Deck(dealerCards);
        Dealer dealer = new Dealer();
        giveCardToPlayer(dealer.getPlayer(), deck, dealerCards.size());
        Player player = generateBustedPlayer();

        GameResultBoard gameResultBoard = new GameResultBoard(dealer, new Players(List.of(player)));

        assertThat(gameResultBoard.getGameResult(player)).isEqualTo(expected);
    }

    static Stream<Arguments> dealerCardsAndExpectedProfitWhenPlayerBusted() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, ACE), new Card(DIAMOND, QUEEN)),
                        calculateExpectedProfitOf(GameResult.LOSE)
                ),
                Arguments.arguments(
                        List.of(new Card(SPADE, TWO), new Card(CLOVER, TWO)),
                        calculateExpectedProfitOf(GameResult.LOSE)
                ),
                Arguments.arguments(
                        List.of(new Card(HEART, TWO), new Card(CLOVER, QUEEN), new Card(SPADE, KING)),
                        calculateExpectedProfitOf(GameResult.LOSE)
                )
        );
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭인 경우 플레이어의 손익은 0이다.")
    void bothBlackJackTest() {
        List<Card> bothBlackJackCards = List.of(
                new Card(SPADE, ACE), new Card(CLOVER, KING),
                new Card(HEART, ACE), new Card(DIAMOND, QUEEN)
        );
        Deck deck = new Deck(bothBlackJackCards);
        Player player = Player.from("testPlayer", 0);
        Dealer dealer = new Dealer();
        giveCardToPlayer(player, deck, 2);
        giveCardToPlayer(dealer.getPlayer(), deck, 2);

        GameResultBoard gameResultBoard = new GameResultBoard(dealer, new Players(List.of(player)));

        int expected = calculateExpectedProfitOf(GameResult.DRAW);
        assertThat(gameResultBoard.getGameResult(player)).isEqualTo(expected);
    }

//
//    @Test
//    @DisplayName("딜러의 전적을 반환할 수 있다.")
//    void calculateDealerResultTest() {
//        List<Card> cards = generateCards();
//        Deck deck = new Deck(cards);
//
//        Dealer dealer = new Dealer();
//        giveCardToPlayer(dealer.getPlayer(), deck, 2);
//
//        List<Player> players = generatePlayers();
//        players.forEach(player -> giveCardToPlayer(player, deck, 2));
//
//        GameResultBoard gameResultBoard = new GameResultBoard(dealer, new Players(players));
//        Map<GameResult, Integer> dealerResult = gameResultBoard.getDealerResult();
//
//        assertAll(
//                () -> assertThat(dealerResult).containsEntry(GameResult.WIN, 1),
//                () -> assertThat(dealerResult).containsEntry(GameResult.DRAW, 1),
//                () -> assertThat(dealerResult).containsEntry(GameResult.LOSE, 2)
//        );
//    }
//
//    private List<Card> generateCards() {
//        return List.of(
//                new Card(DIAMOND, KING), new Card(DIAMOND, NINE), // Dealer
//                new Card(DIAMOND, ACE), new Card(DIAMOND, QUEEN), // dealer lose
//                new Card(SPADE, ACE), new Card(SPADE, QUEEN), // dealer lose
//                new Card(SPADE, KING), new Card(SPADE, NINE), // draw
//                new Card(SPADE, TEN), new Card(SPADE, EIGHT) // dealer win
//        );
//    }
//
//    private List<Player> generatePlayers() {
//        List<String> playerNames = List.of("loki", "pedro", "poke", "alpaca");
//        return playerNames.stream()
//                .map(name -> Player.from(name, TEST_PLAYER_BET_AMOUNT))
//                .toList();
//    }
}

package blackjack.domain;

import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Shape.SPADE;
import static blackjack.domain.card.Value.ACE;
import static blackjack.domain.card.Value.EIGHT;
import static blackjack.domain.card.Value.KING;
import static blackjack.domain.card.Value.NINE;
import static blackjack.domain.card.Value.QUEEN;
import static blackjack.domain.card.Value.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultBoardTest {
    @ParameterizedTest
    @MethodSource("playerAndGameResult")
    @DisplayName("딜러와 플레이어들을 전달받아 승리/패배/무승부 여부를 계산한다.")
    void calculateGameResultTest(List<Card> playerCards, GameResult expected) {
        List<Card> cards = new ArrayList<>(List.of(new Card(DIAMOND, KING), new Card(DIAMOND, NINE)));
        cards.addAll(playerCards);
        Deck deck = new Deck(cards);

        Dealer dealer = new Dealer();
        giveCardToPlayer(dealer.getPlayer(), deck, 2);
        Player player = new Player("testPlayer");
        giveCardToPlayer(player, deck, 2);

        GameResultBoard gameResultBoard = new GameResultBoard(dealer, List.of(player));

        assertThat(gameResultBoard.getGameResult(player)).isEqualTo(expected);
    }

    static Stream<Arguments> playerAndGameResult() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, ACE), new Card(DIAMOND, QUEEN)), GameResult.WIN
                ),
                Arguments.arguments(
                        List.of(new Card(SPADE, ACE), new Card(SPADE, QUEEN)), GameResult.WIN
                ),
                Arguments.arguments(
                        List.of(new Card(SPADE, KING), new Card(SPADE, NINE)), GameResult.DRAW
                ),
                Arguments.arguments(
                        List.of(new Card(SPADE, TEN), new Card(SPADE, EIGHT)), GameResult.LOSE
                )
        );
    }

    @Test
    @DisplayName("딜러의 전적을 반환할 수 있다.")
    void calculateDealerResultTest() {
        List<Card> cards = generateCards();
        Deck deck = new Deck(cards);

        Dealer dealer = new Dealer();
        giveCardToPlayer(dealer.getPlayer(), deck, 2);

        List<Player> players = generatePlayers();
        players.forEach(player -> giveCardToPlayer(player, deck, 2));

        GameResultBoard gameResultBoard = new GameResultBoard(dealer, players);
        Map<GameResult, Integer> dealerResult = gameResultBoard.getDealerResult();

        assertAll(
                () -> assertThat(dealerResult).containsEntry(GameResult.WIN, 1),
                () -> assertThat(dealerResult).containsEntry(GameResult.DRAW, 1),
                () -> assertThat(dealerResult).containsEntry(GameResult.LOSE, 2)
        );
    }

    private List<Card> generateCards() {
        return List.of(
                new Card(DIAMOND, KING), new Card(DIAMOND, NINE), // Dealer
                new Card(DIAMOND, ACE), new Card(DIAMOND, QUEEN), // dealer lose
                new Card(SPADE, ACE), new Card(SPADE, QUEEN), // dealer lose
                new Card(SPADE, KING), new Card(SPADE, NINE), // draw
                new Card(SPADE, TEN), new Card(SPADE, EIGHT) // dealer win
        );
    }

    private List<Player> generatePlayers() {
        List<String> playerNames = List.of("loki", "pedro", "poke", "alpaca");
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void giveCardToPlayer(Player player, Deck deck, int drawAmount) {
        for (int i = 0; i < drawAmount; i++) {
            player.draw(deck);
        }
    }
}

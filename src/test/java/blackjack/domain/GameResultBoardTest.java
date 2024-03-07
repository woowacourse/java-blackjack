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

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
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
        Deck deck = Deck.from(cards);

        Player player = new Player("testPlayer");
        Dealer dealer = new Dealer();
        drawCards(dealer, player, deck);

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

    private static void drawCards(Dealer dealer, Player player, Deck deck) {
        dealer.draw(deck);
        dealer.draw(deck);
        player.draw(deck);
        player.draw(deck);
    }
}
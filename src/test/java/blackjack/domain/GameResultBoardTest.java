package blackjack.domain;

import static blackjack.domain.card.Shape.CLOVER;
import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Shape.SPADE;
import static blackjack.domain.card.Value.ACE;
import static blackjack.domain.card.Value.EIGHT;
import static blackjack.domain.card.Value.KING;
import static blackjack.domain.card.Value.NINE;
import static blackjack.domain.card.Value.QUEEN;
import static blackjack.domain.card.Value.TEN;
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameResultBoard;
import java.util.ArrayList;
import java.util.List;
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
        GameResult gameResult = gameResultBoard.findByPlayerName(player.getPlayerName());
        assertThat(gameResult).isEqualTo(expected);
    }

    private static Stream<Arguments> playerAndGameResult() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(CLOVER, KING), new Card(CLOVER, QUEEN)), GameResult.WIN
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
    @DisplayName("딜러와 플레이어들 모두 버스트가 되면 플레이어가 패배한다.")
    void calculateGameResultWhenBustTest() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(DIAMOND, KING), new Card(DIAMOND, QUEEN), new Card(DIAMOND, NINE),
                        new Card(SPADE, TWO), new Card(SPADE, QUEEN), new Card(SPADE, KING)));
        Deck deck = new Deck(cards);

        Dealer dealer = new Dealer();
        giveCardToPlayer(dealer.getPlayer(), deck, 3);
        Player player = new Player("testPlayer");
        giveCardToPlayer(player, deck, 3);

        GameResultBoard gameResultBoard = new GameResultBoard(dealer, List.of(player));
        GameResult gameResult = gameResultBoard.findByPlayerName(player.getPlayerName());
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이기는 경우가 있다.")
    void calculateGameResultWhenPlayerBlackJackTest() {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(DIAMOND, KING), new Card(DIAMOND, QUEEN), new Card(DIAMOND, NINE),
                        new Card(SPADE, ACE), new Card(SPADE, KING)));
        Deck deck = new Deck(cards);

        Dealer dealer = new Dealer();
        giveCardToPlayer(dealer.getPlayer(), deck, 3);
        Player player = new Player("testPlayer");
        giveCardToPlayer(player, deck, 2);

        GameResultBoard gameResultBoard = new GameResultBoard(dealer, List.of(player));
        GameResult gameResult = gameResultBoard.findByPlayerName(player.getPlayerName());
        assertThat(gameResult).isEqualTo(GameResult.WIN_BY_BLACK_JACK);
    }

    private void giveCardToPlayer(Player player, Deck deck, int drawAmount) {
        for (int i = 0; i < drawAmount; i++) {
            player.hit(deck);
        }
    }
}

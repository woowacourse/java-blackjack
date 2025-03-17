package domain.participant;

import static domain.blackJack.MatchResult.BLACKJACK;
import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;
import static domain.card.Number.ACE;
import static domain.card.Number.FIVE;
import static domain.card.Number.FOUR;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Number.THREE;
import static domain.card.Number.TWO;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.blackJack.MatchResult;
import domain.card.Card;
import domain.card.CardDeck;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputView;
import view.OutputView;

public class PlayerTest {
    @Test
    @DisplayName("카드 합계 테스트")
    void sumTest() {
        // given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Player player = new Player("pobi", Money.from(1000));

        // when
        player.hitCard(cardDeck);
        player.hitCard(cardDeck);

        // then
        assertThat(player.sum()).isEqualTo(12);
    }

    @ParameterizedTest
    @DisplayName("드로우 테스트")
    @ValueSource(strings = {"y\ny\ny\ny\n", "y\ny\ny\nn\n"})
    void drawTest(String input) {
        // given
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputView testInputView = new InputView(new Scanner(System.in));
        OutputView testOutputView = new OutputView();

        CardDeck cardDeck = new CardDeck(
                List.of(new Card(DIAMOND, QUEEN), new Card(SPADE, JACK), new Card(HEART, KING)));
        Player player = new Player("pobi", Money.from(1000));

        // when
        player.draw(testInputView::askPlayerForHitOrStand, testOutputView::printPlayerDeck, cardDeck);

        // then
        assertThat(player.getHand().getCards().size()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideMatchResultForProfit")
    @DisplayName("최종 수익 테스트")
    void calculateProfitTest(CardDeck cardDeck, int profit) {
        // given
        Player player = new Player("pobi", Money.from(10000));
        Dealer dealer = new Dealer();

        player.hitCard(cardDeck);
        player.hitCard(cardDeck);

        dealer.hitCard(cardDeck);
        dealer.hitCard(cardDeck);

        // when-then
        assertThat(player.calculateProfit(dealer)).isEqualTo(profit);
    }

    private static Stream<Arguments> provideMatchResultForProfit() {
        return Stream.of(
                Arguments.of(new CardDeck( // 21, 20, BLACKJACK
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE),
                                new Card(HEART, JACK), new Card(HEART, KING))), 15000),
                Arguments.of(new CardDeck( // 21, 21, DRAW
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE),
                                new Card(HEART, JACK), new Card(HEART, ACE))), 0),
                Arguments.of(new CardDeck( // 20, 10, WIN
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING),
                                new Card(HEART, TWO), new Card(HEART, THREE))), 10000),
                Arguments.of(new CardDeck( // 10, 20, LOSE
                        List.of(new Card(SPADE, TWO), new Card(SPADE, THREE),
                                new Card(HEART, JACK), new Card(HEART, KING))), -10000)
        );
    }

    @ParameterizedTest
    @DisplayName("결과 산출 테스트")
    @MethodSource("provideCardDeckForCalculateResultOfPlayer")
    void calculateResultOfPlayerTest(CardDeck cardDeck, MatchResult matchResult) {
        // given
        Player player = new Player("pobi", Money.from(1000));
        Dealer dealer = new Dealer();

        player.hitCard(cardDeck);
        player.hitCard(cardDeck);
        player.hitCard(cardDeck);

        dealer.hitCard(cardDeck);
        dealer.hitCard(cardDeck);
        dealer.hitCard(cardDeck);

        // when-then
        assertThat(player.calculateResult(dealer)).isEqualTo(matchResult);
    }

    private static Stream<Arguments> provideCardDeckForCalculateResultOfPlayer() {
        return Stream.of(
                Arguments.of(new CardDeck( // 25, 25, LOSE
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING), new Card(SPADE, FIVE),
                                new Card(HEART, JACK), new Card(HEART, KING), new Card(HEART, FIVE))), LOSE),
                Arguments.of(new CardDeck( // 25, 27, LOSE
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING), new Card(SPADE, FIVE),
                                new Card(HEART, JACK), new Card(HEART, KING), new Card(HEART, SEVEN))), LOSE),
                Arguments.of(new CardDeck( // 25, 20, LOSE
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING), new Card(SPADE, FIVE),
                                new Card(HEART, TWO), new Card(HEART, THREE), new Card(HEART, FIVE))), LOSE),
                Arguments.of(new CardDeck( // 10, 20, LOSE
                        List.of(new Card(SPADE, TWO), new Card(SPADE, THREE), new Card(SPADE, FIVE),
                                new Card(HEART, JACK), new Card(HEART, FOUR), new Card(HEART, SIX))), LOSE),
                Arguments.of(new CardDeck( // 21, 23, WIN
                        List.of(new Card(SPADE, JACK), new Card(SPADE, KING), new Card(SPADE, ACE),
                                new Card(HEART, JACK), new Card(HEART, KING), new Card(HEART, THREE))), WIN),
                Arguments.of(new CardDeck( // 20, 10, WIN
                        List.of(new Card(SPADE, JACK), new Card(SPADE, FOUR), new Card(SPADE, SIX),
                                new Card(HEART, TWO), new Card(HEART, THREE), new Card(HEART, FIVE))), WIN),
                Arguments.of(new CardDeck( // 20, 20, DRAW
                        List.of(new Card(SPADE, JACK), new Card(SPADE, FOUR), new Card(SPADE, SIX),
                                new Card(HEART, JACK), new Card(HEART, FOUR), new Card(HEART, SIX))), DRAW),
                Arguments.of(new CardDeck( // 21, 21, DRAW
                        List.of(new Card(SPADE, JACK), new Card(SPADE, FIVE), new Card(SPADE, SIX),
                                new Card(HEART, JACK), new Card(HEART, FIVE), new Card(HEART, SIX))), DRAW)
        );
    }

    @ParameterizedTest
    @DisplayName("블랙잭 테스트")
    @MethodSource("provideCardDeckForCalculateBlackJackOfPlayer")
    void calculateBlackJackOfPlayerTest(CardDeck cardDeck, MatchResult matchResult) {
        // given
        Player player = new Player("pobi", Money.from(1000));
        Dealer dealer = new Dealer();

        player.hitCard(cardDeck);
        player.hitCard(cardDeck);

        dealer.hitCard(cardDeck);
        dealer.hitCard(cardDeck);

        // when-then
        assertThat(player.calculateResult(dealer)).isEqualTo(matchResult);
    }

    private static Stream<Arguments> provideCardDeckForCalculateBlackJackOfPlayer() {
        return Stream.of(
                Arguments.of(new CardDeck( // 21, 20, BLACKJACK
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE),
                                new Card(HEART, JACK), new Card(HEART, KING))), BLACKJACK),
                Arguments.of(new CardDeck( // 21, 21, DRAW
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE),
                                new Card(HEART, JACK), new Card(HEART, ACE))), DRAW)
        );
    }
}

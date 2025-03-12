package domain.participant;

import static domain.blackJack.MatchResult.BLACKJACK;
import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;
import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;
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
    void calculateProfitTest(MatchResult matchResult, int profit) {
        // given
        Player player = new Player("pobi", Money.from(10000));

        // when-then
        assertThat(player.calculateProfit(matchResult)).isEqualTo(profit);
    }

    private static Stream<Arguments> provideMatchResultForProfit(){
        return Stream.of(
                Arguments.of(WIN, 10000),
                Arguments.of(DRAW, 0),
                Arguments.of(BLACKJACK, 5000),
                Arguments.of(LOSE, -10000)
        );
    }
}

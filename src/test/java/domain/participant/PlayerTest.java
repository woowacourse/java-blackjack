package domain.participant;

import static domain.blackJack.MatchResult.WIN;
import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;
import static domain.card.Number.THREE;
import static domain.card.Number.TWO;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.blackJack.Result;
import domain.card.Card;
import domain.card.CardDeck;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

//    @Test
//    @DisplayName("승패 결정 테스트")
//    void calculateWinner() {
//        // given
//        Player player = new Player("pobi", Money.from(1000));
//        CardDeck cardDeck = new CardDeck(
//                List.of(new Card(DIAMOND, JACK), new Card(SPADE, ACE), new Card(HEART, TWO), new Card(DIAMOND, THREE)));
//        Dealer dealer = new Dealer();
//
//        player.hitCard(cardDeck);
//        player.hitCard(cardDeck);
//
//        dealer.hitCard(cardDeck);
//        dealer.hitCard(cardDeck);
//
//        // when-then
//        assertThat(player.calculateWinner(dealer.sum())).isEqualTo(WIN);
//    }
}

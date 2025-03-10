package domain.participant;

import static domain.MatchResult.LOSE;
import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.CardDeckFactory;
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
    @DisplayName("카드 두 장 뽑기 테스트")
    void hitCardsTest() {
        // given
        Player player = new Player("pobi");
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);

        // when
        player.hitCards(dealer);

        // then
        assertDoesNotThrow(() -> player.hitCards(dealer));
    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardTest() {
        //given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);
        Player player = new Player("pobi");

        //when-then
        assertDoesNotThrow(() -> player.addCard(dealer));
    }

    @Test
    @DisplayName("카드 합계 테스트")
    void sumTest() {
        //given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(cardDeck);
        Player player = new Player("pobi");

        //when
        player.hitCards(dealer);

        //then
        assertThat(player.sum()).isEqualTo(12);
    }

    @ParameterizedTest
    @DisplayName("드로우 테스트")
    @ValueSource(strings = {"y\ny\ny\n", "y\nn\n"})
    void drawTest(String input) {
        //given
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputView testInputView = new InputView(new Scanner(System.in));
        OutputView testOutputView = new OutputView();

        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, QUEEN), new Card(SPADE, JACK), new Card(HEART, KING)));
        Dealer dealer = new Dealer(cardDeck);
        Player player = new Player("pobi");

        //when-then
        assertDoesNotThrow(() -> player.draw(testInputView::askPlayerForHitOrStand, testOutputView::printPlayerDeck, dealer));
    }

    @Test
    @DisplayName("승패 결정 테스트")
    void calculateWinner() {
        //given
        Player player = new Player("pobi");
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, JACK), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(cardDeck);
        player.addCard(dealer);
        dealer.addCards();

        //when-then
        assertThat(player.calculateWinner(dealer.sum())).isEqualTo(LOSE);
    }
}

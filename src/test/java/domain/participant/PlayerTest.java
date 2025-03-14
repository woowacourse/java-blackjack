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
import domain.card.Deck;
import domain.card.Hand;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
        Hand hand = new Hand(new ArrayList<>());
        Player player = new Player(hand, new Name("pobi"));
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Deck cardDeck = cardDeckFactory.create();

        // when - then
        assertDoesNotThrow(() -> player.hitCards(cardDeck));
    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardTest() {
        //given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Deck cardDeck = cardDeckFactory.create();
        Hand hand = new Hand(new ArrayList<>());
        Player player = new Player(hand, new Name("pobi"));

        //when-then
        assertDoesNotThrow(() -> player.addCard(cardDeck.hitCard()));
    }

    @Test
    @DisplayName("카드 합계 테스트")
    void calculateSumTest() {
        //given
        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Hand hand = new Hand(new ArrayList<>());
        Player player = new Player(hand, new Name("pobi"));

        //when
        player.hitCards(cardDeck);

        //then
        assertThat(player.calculateSum()).isEqualTo(12);
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

        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, QUEEN), new Card(SPADE, JACK), new Card(HEART, KING)));
        Hand hand = new Hand(new ArrayList<>());
        Player player = new Player(hand, new Name("pobi"));

        //when-then
        assertDoesNotThrow(() -> player.draw(testInputView::askPlayerForHitOrStand, testOutputView::printPlayerDeck, cardDeck));
    }

    @Test
    @DisplayName("승패 결정 테스트")
    void calculateWinner() {
        //given
        Player player = new Player(new Hand(new ArrayList<>()), new Name("pobi"));
        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, JACK), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        player.addCard(cardDeck.hitCard());
        dealer.addCard(cardDeck.hitCard());

        //when-then
        assertThat(player.calculateWinner(dealer.calculateSum())).isEqualTo(LOSE);
    }
}

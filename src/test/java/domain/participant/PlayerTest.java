package domain.participant;

import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.NINE;
import static domain.card.Number.QUEEN;
import static domain.card.Number.TEN;
import static domain.card.Number.TWO;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.DeckFactory;
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
        Player player = new Player(hand, new Name("pobi"), new Money(10000));
        DeckFactory deckFactory = new DeckFactory();
        Deck cardDeck = deckFactory.create();

        // when - then
        assertDoesNotThrow(() -> player.hitCards(cardDeck));
    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardTest() {
        //given
        DeckFactory deckFactory = new DeckFactory();
        Deck cardDeck = deckFactory.create();
        Hand hand = new Hand(new ArrayList<>());
        Player player = new Player(hand, new Name("pobi"), new Money(10000));

        //when-then
        assertDoesNotThrow(() -> player.addCard(cardDeck.hitCard()));
    }

    @Test
    @DisplayName("카드 합계 테스트")
    void calculateSumTest() {
        //given
        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Hand hand = new Hand(new ArrayList<>());
        Player player = new Player(hand, new Name("pobi"), new Money(10000));

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
        Player player = new Player(hand, new Name("pobi"), new Money(10000));

        //when-then
        assertDoesNotThrow(() -> player.draw(testInputView::askPlayerForHitOrStand, testOutputView::printPlayerDeck, cardDeck));
    }

    @Test
    @DisplayName("플레이어 초기 카드 오픈 테스트")
    void openInitialCardsTest() {
        Player player = new Player(new Hand(new ArrayList<>()), new Name("pobi"), new Money(10000));
        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, JACK), new Card(SPADE, ACE)));
        player.addCard(cardDeck.hitCard());
        player.addCard(cardDeck.hitCard());

        assertThat(player.openInitialCards().getFirst()).isEqualTo(new Card(DIAMOND, JACK));
        assertThat(player.openInitialCards().get(1)).isEqualTo(new Card(SPADE, ACE));
    }

    @Test
    @DisplayName("수익률 계산 테스트 (승리)")
    void calculateProfitTest() {
        Player player = new Player(new Hand(List.of()), new Name("pobi"), new Money(10000));
        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, JACK), new Card(SPADE, QUEEN)));
        player.addCard(cardDeck.hitCard());
        player.addCard(cardDeck.hitCard());

        Dealer dealer = new Dealer(new Hand(List.of()));

        assertThat(player.calculateProfit(dealer.calculateSum())).isEqualTo(10000);
    }

    @Test
    @DisplayName("수익률 계산 테스트 (블랙잭이면서 승리)")
    void calculateBlackJackProfitTest() {
        Player player = new Player(new Hand(List.of()), new Name("pobi"), new Money(10000));
        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, TEN), new Card(SPADE, NINE), new Card(DIAMOND, TWO)));
        player.addCard(cardDeck.hitCard());
        player.addCard(cardDeck.hitCard());
        player.addCard(cardDeck.hitCard());

        Dealer dealer = new Dealer(new Hand(List.of()));

        assertThat(player.calculateProfit(dealer.calculateSum())).isEqualTo(15000);
    }

    @Test
    @DisplayName("수익률 계산 테스트 (패배)")
    void calculateLoseProfitTest() {
        Player player = new Player(new Hand(List.of()), new Name("pobi"), new Money(10000));
        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, TWO), new Card(SPADE, QUEEN)));
        Dealer dealer = new Dealer(new Hand(List.of()));

        player.addCard(cardDeck.hitCard());
        dealer.addCard(cardDeck.hitCard());

        assertThat(player.calculateProfit(dealer.calculateSum())).isEqualTo(-10000);
    }

    @Test
    @DisplayName("수익률 계산 테스트 (무승부)")
    void calculateDrawProfitTest() {
        Player player = new Player(new Hand(List.of()), new Name("pobi"), new Money(10000));
        Deck cardDeck = new Deck(List.of(new Card(DIAMOND, TWO), new Card(SPADE, TWO)));
        Dealer dealer = new Dealer(new Hand(List.of()));

        player.addCard(cardDeck.hitCard());
        dealer.addCard(cardDeck.hitCard());

        assertThat(player.calculateProfit(dealer.calculateSum())).isEqualTo(0);
    }
}

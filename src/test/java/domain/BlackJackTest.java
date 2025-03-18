package domain;

import static domain.card.Number.ACE;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.DeckFactory;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Players;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

public class BlackJackTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsToParticipantTest(){
        //given
        DeckFactory deckFactory = new DeckFactory();
        Deck deck = deckFactory.create();
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        Map<Name, Money> playerBet = new LinkedHashMap<>(Map.of(new Name("pobi"), new Money(10000), new Name("lisa"), new Money(20000)));
        Players players = Players.from(playerBet);

        //when
        BlackJack blackJack = new BlackJack(players, dealer);

        //then
        assertDoesNotThrow(() -> blackJack.hitCardsToParticipant(deck));
    }

    @Test
    @DisplayName("카드 드로우 테스트")
    void drawPlayersTest(){
        //given
        String input = "y\nn\ny\nn\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputView testInputView = new InputView(new Scanner(System.in));
        OutputView testOutputView = new OutputView();

        DeckFactory deckFactory = new DeckFactory();
        Deck deck = deckFactory.create();
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        Map<Name, Money> playerBet = new LinkedHashMap<>(Map.of(new Name("pobi"), new Money(10000), new Name("lisa"), new Money(20000)));
        Players players = Players.from(playerBet);

        BlackJack blackJack = new BlackJack(players, dealer);

        //when-then
        assertDoesNotThrow(() -> blackJack.drawPlayers(testInputView::askPlayerForHitOrStand, testOutputView::printPlayerDeck, deck));
    }

    @Test
    @DisplayName("딜러 드로우 테스트")
    void drawDealerTest(){
        //given
        DeckFactory deckFactory = new DeckFactory();
        Deck deck = deckFactory.create();
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        Map<Name, Money> playerBet = new LinkedHashMap<>(Map.of(new Name("pobi"), new Money(10000), new Name("lisa"), new Money(20000)));
        Players players = Players.from(playerBet);

        //when
        BlackJack blackJack = new BlackJack(players, dealer);

        //then
        assertDoesNotThrow(() -> blackJack.drawDealer(deck));
    }

    @Test
    @DisplayName("결과 선출 테스트")
    void calculatePlayerProfitTest() {
        //given
        Map<Name, Money> playerBet = new LinkedHashMap<>(Map.of(new Name("pobi"), new Money(10000), new Name("lisa"), new Money(20000)));
        Players players = Players.from(playerBet);
        Deck deck = new Deck(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, FIVE), new Card(DIAMOND, ACE), new Card(SPADE, JACK), new Card(HEART, ACE), new Card(CLOVER, ACE)));
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));

        BlackJack blackJack = new BlackJack(players, dealer);

        //when
        blackJack.hitCardsToParticipant(deck);

        //then
        assertDoesNotThrow(blackJack::calculatePlayerProfit);
    }

    @Test
    @DisplayName("딜러 결과 산출 테스트")
    void calculateDealerProfit() {
        //given
        Map<Name, Money> playerBet = new LinkedHashMap<>(Map.of(new Name("pobi"), new Money(10000)));
        Deck deck = new Deck(List.of(new Card(DIAMOND, QUEEN), new Card(SPADE, JACK)));
        Players players = Players.from(playerBet);
        Dealer dealer = new Dealer(new Hand(List.of()));
        BlackJack blackJack = new BlackJack(players, dealer);

        //when
        players.getPlayers().getFirst().addCard(deck.hitCard());

        //then
        assertThat(blackJack.calculateDealerProfit()).isEqualTo(-10000);
    }
}

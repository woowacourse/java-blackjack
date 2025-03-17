package domain.blackJack;

import static domain.card.Number.ACE;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Number.THREE;
import static domain.card.Number.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import domain.participant.Players;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

public class BlackJackTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsToParticipantTest() {
        //given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Players players = Players.from(
                List.of(new Player("pobi", Money.from(1000)), new Player("lisa", Money.from(1000))));
        Dealer dealer = new Dealer();

        //when
        BlackJack blackJack = new BlackJack(players, dealer, cardDeckFactory.create());
        blackJack.hitCardsToParticipant();

        //then
        assertSoftly(softly -> {
            softly.assertThat(players.getPlayers().getFirst().getHand().getCards().size()).isEqualTo(2);
            softly.assertThat(dealer.getHand().getCards().size()).isEqualTo(2);
        });
    }

    @Test
    @DisplayName("카드 드로우 테스트")
    void drawPlayersTest() {
        //given
        String input = "y\nn\ny\nn\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputView testInputView = new InputView(new Scanner(System.in));
        OutputView testOutputView = new OutputView();

        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Players players = Players.from(
                List.of(new Player("pobi", Money.from(1000)), new Player("lisa", Money.from(1000))));

        BlackJack blackJack = new BlackJack(players, new Dealer(), cardDeckFactory.create());

        //when
        blackJack.drawPlayers(testInputView::askPlayerForHitOrStand, testOutputView::printPlayerDeck);

        //then
        assertThat(players.getPlayers().getFirst().getHand().getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러 드로우 테스트")
    void drawDealerTest() {
        //given
        CardDeck cardDeck = new CardDeck(
                List.of(new Card(SPADE, TWO), new Card(DIAMOND, THREE), new Card(DIAMOND, FIVE),
                        new Card(SPADE, JACK)));

        Players players = Players.from(
                List.of(new Player("pobi", Money.from(1000)), new Player("lisa", Money.from(1000))));
        Dealer dealer = new Dealer();

        //when
        BlackJack blackJack = new BlackJack(players, dealer, cardDeck);
        blackJack.drawDealer();

        //then
        assertThat(dealer.getHand().getCards().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("결과 선출 테스트")
    void calculatePlayerResultTest() {
        //given
        Players players = Players.from(
                List.of(new Player("pobi", Money.from(1000)), new Player("lisa", Money.from(1000))));
        CardDeck cardDeck = new CardDeck(
                List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, FIVE), new Card(DIAMOND, ACE), new Card(SPADE, JACK),
                        new Card(HEART, ACE), new Card(CLOVER, ACE)));

        BlackJack blackJack = new BlackJack(players, new Dealer(), cardDeck);

        //when
        blackJack.hitCardsToParticipant();
        Map<Player, Integer> playerMatchResultMap = blackJack.calculatePlayerResult();

        //then
        SoftAssertions softAssertions = new SoftAssertions();
        playerMatchResultMap.forEach((key, value) -> softAssertions.assertThat(value).isEqualTo(1000));
    }

    @Test
    void calculateDealerResultTest() {
        //given
        Players players = Players.from(
                List.of(new Player("pobi", Money.from(1000)), new Player("lisa", Money.from(1000))));
        CardDeck cardDeck = new CardDeck(
                List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, FIVE),
                        new Card(DIAMOND, ACE), new Card(SPADE, JACK),
                        new Card(HEART, ACE), new Card(CLOVER, ACE)));

        BlackJack blackJack = new BlackJack(players, new Dealer(), cardDeck);

        //when
        blackJack.hitCardsToParticipant();
        int profit = blackJack.calculateDealerResult();

        // then
        assertThat(profit).isEqualTo(-2000);
    }
}

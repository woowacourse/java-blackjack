package domain;

import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static domain.card.Number.ACE;
import static domain.card.Number.EIGHT;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.NINE;
import static domain.card.Number.QUEEN;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

public class BlackJackTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsToParticipantTest(){
        //given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Dealer dealer = new Dealer(cardDeckFactory.create());
        Players players = Players.from(List.of("pobi", "lisa"));

        //when
        BlackJack blackJack = new BlackJack(players, dealer);

        //then
        assertDoesNotThrow(blackJack::hitCardsToParticipant);
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

        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Dealer dealer = new Dealer(cardDeckFactory.create());
        Players players = Players.from(List.of("pobi", "lisa"));

        BlackJack blackJack = new BlackJack(players, dealer);

        //when-then
        assertDoesNotThrow(() -> blackJack.drawPlayers(testInputView::askPlayerForHitOrStand, testInputView::printPlayerDeck));
    }

    @Test
    @DisplayName("딜러 드로우 테스트")
    void drawDealerTest(){
        //given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Dealer dealer = new Dealer(cardDeckFactory.create());
        Players players = Players.from(List.of("pobi", "lisa"));

        //when
        BlackJack blackJack = new BlackJack(players, dealer);

        //then
        assertDoesNotThrow(blackJack::drawDealer);
    }

    @Test
    @DisplayName("결과 선출 테스트")
    void calculateWinnerTest() {
        //given
        Players players = Players.from(List.of("pobi", "lisa"));
        CardDeck cardDeck = new CardDeck(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, FIVE), new Card(DIAMOND, ACE), new Card(SPADE, JACK), new Card(HEART, ACE), new Card(CLOVER, ACE)));
        Dealer dealer = new Dealer(cardDeck);

        BlackJack blackJack = new BlackJack(players, dealer);

        //when
        blackJack.hitCardsToParticipant();
        Map<Player, MatchResult> playerMatchResultMap = blackJack.calculateWinner();

        //then
        SoftAssertions softAssertions = new SoftAssertions();
        playerMatchResultMap.forEach((key, value) -> softAssertions.assertThat(value).isEqualTo(WIN));
    }
}

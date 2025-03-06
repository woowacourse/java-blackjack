package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.CardDeckFactory;
import domain.participant.Dealer;
import domain.participant.Players;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
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
}

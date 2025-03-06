package domain;

import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;
import static domain.card.Number.SEVEN;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
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
    void drawTest(){
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
        assertDoesNotThrow(() -> blackJack.draw(testInputView::askPlayerForHitOrStand, testInputView::printPlayerDeck));
    }
}

package domain.participant;

import static domain.card.Number.JACK;
import static domain.card.Number.KING;
import static domain.card.Number.QUEEN;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import view.InputView;
import view.OutputView;

public class PlayersTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsTest(){
        //given
        Names names = new Names(List.of("pobi", "lisa"));
        Players players = Players.from(names);

        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Deck deck = cardDeckFactory.create();

        //when-then

//        assertSoftly(softly -> {
//            softly.assertThat(playerList.getFirst().getCardDeck().getCardsSize()).isEqualTo(2);
//            softly.assertThat(playerList.get(1).getCardDeck().getCardsSize()).isEqualTo(2);
//        });

        assertDoesNotThrow(() -> players.hitCards(deck));

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
        OutputView testOutputView = new OutputView();

        Deck deck = new Deck(List.of(new Card(DIAMOND, QUEEN), new Card(SPADE, JACK), new Card(HEART, KING)));
        Names names = new Names(List.of("pobi", "lisa"));
        Players players = Players.from(names);

        //when-then
        assertDoesNotThrow(() -> players.draw(testInputView::askPlayerForHitOrStand, testOutputView::printPlayerDeck, deck));
    }

}

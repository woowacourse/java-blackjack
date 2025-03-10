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
import domain.card.CardDeck;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
        Players players = Players.from(new ArrayList<>(
                List.of("pobi", "lisa")
        ));

        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();

        Dealer dealer = new Dealer(cardDeck);

        //when-then

//        assertSoftly(softly -> {
//            softly.assertThat(playerList.getFirst().getCardDeck().getCardsSize()).isEqualTo(2);
//            softly.assertThat(playerList.get(1).getCardDeck().getCardsSize()).isEqualTo(2);
//        });

        assertDoesNotThrow(() -> players.hitCards(dealer));

    }

    @ParameterizedTest
    @DisplayName("플레이어 인원 검증 테스트")
    @MethodSource("provideListForPlayers")
    void validatePlayerNumbers(List<String> names) {
        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 인원은 1~6명 입니다.");
    }

    private static Stream<Arguments> provideListForPlayers(){
        return Stream.of(Arguments.of(
                List.of("포비", "이든", "네오", "저스틴", "링크", "말론", "리사"),
                List.of()
        ));
    }

    @Test
    @DisplayName("중복된 이름 테스트")
    void validateIsDuplicateTest() {
        assertThatThrownBy(() -> Players.from(List.of("pobi", "pobi")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복된 이름의 플레이어가 게임에 참여할 수 없습니다.");
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

        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, QUEEN), new Card(SPADE, JACK), new Card(HEART, KING)));
        Dealer dealer = new Dealer(cardDeck);
        Players players = Players.from(List.of("pobi", "lisa"));

        //when-then
        assertDoesNotThrow(() -> players.draw(testInputView::askPlayerForHitOrStand, testOutputView::printPlayerDeck, dealer));
    }
}

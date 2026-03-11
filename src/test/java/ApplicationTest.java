import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Deck;
import domain.TestBlackjackGame;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;
import vo.Rank;
import vo.Suit;

public class ApplicationTest {
    private static final List<Card> FIXED_CARDS = List.of(
            new Card(Suit.SPADE, Rank.KING),
            new Card(Suit.HEART, Rank.TWO),
            new Card(Suit.DIAMOND, Rank.FIVE),
            new Card(Suit.SPADE, Rank.ACE),
            new Card(Suit.HEART, Rank.THREE),
            new Card(Suit.DIAMOND, Rank.SIX),
            new Card(Suit.CLUB, Rank.SEVEN)
    );

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpOutputStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreOutputStream() {
        System.setOut(originalOut);
    }

    @Test
    void 게임_전체_흐름_출력_검증() {
        // given
        String input = "영기,라이\n1000\n1000\nn\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Application app = new Application(
                new InputView(), new OutputView(),
                new TestBlackjackGame(new Deck(FIXED_CARDS))
        );

        // when
        app.run();
        String output = outContent.toString();

        // then
        assertThat(output).contains("영기", "라이", "딜러");
        assertThat(output).contains("K스페이드", "A스페이드");
        assertThat(output).contains("최종 수익");
    }

    @Test
    void 초기_카드_배분_메시지_출력() {
        // given
        String input = "영기,라이\n1000\n1000\nn\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Application app = new Application(
                new InputView(), new OutputView(),
                new TestBlackjackGame(new Deck(FIXED_CARDS))
        );

        // when
        app.run();
        String output = outContent.toString();

        // then
        assertThat(output).contains("영기, 라이에게 2장을 나누었습니다.");
    }

    @Test
    void 잘못된_참가자_입력_재시도() {
        // given
        String input = "123\n영기,라이\n1000\n1000\nn\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Application app = new Application(
                new InputView(), new OutputView(),
                new TestBlackjackGame(new Deck(FIXED_CARDS))
        );

        // when
        app.run();
        String output = outContent.toString();

        // then
        assertThat(output).contains("[ERROR]");
        assertThat(output).contains("영기", "라이");
    }
}

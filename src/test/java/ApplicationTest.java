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
import util.Parser;
import util.Validator;
import view.InputView;
import view.OutputView;
import vo.Rank;
import vo.Suit;

public class ApplicationTest {
    // 카드 배분 순서:
    // 영기: KING(10) + ACE(11) = 21 블랙잭
    // 라이: TWO(2) + THREE(3) = 5
    // 딜러: FIVE(5) + SIX(6) = 11 → 히트 → SEVEN(7) = 18
    private static final List<Card> FIXED_CARDS = List.of(
            new Card(Suit.SPADE, Rank.KING),    // 영기 round1
            new Card(Suit.HEART, Rank.TWO),     // 라이 round1
            new Card(Suit.DIAMOND, Rank.FIVE),  // 딜러 round1
            new Card(Suit.SPADE, Rank.ACE),     // 영기 round2
            new Card(Suit.HEART, Rank.THREE),   // 라이 round2
            new Card(Suit.DIAMOND, Rank.SIX),   // 딜러 round2
            new Card(Suit.CLUB, Rank.SEVEN)     // 딜러 히트
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
        String input = "영기,라이\nn\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Application app = new Application(
                new InputView(), new OutputView(),
                new Parser(), new Validator(),
                new TestBlackjackGame(new Deck(FIXED_CARDS))
        );

        // when
        app.run();
        String output = outContent.toString();

        // then
        assertThat(output).contains("영기", "라이", "딜러");
        assertThat(output).contains("K스페이드", "A스페이드");
        assertThat(output).contains("승", "패");
    }

    @Test
    void 초기_카드_배분_메시지_출력() {
        // given
        String input = "영기,라이\nn\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Application app = new Application(
                new InputView(), new OutputView(),
                new Parser(), new Validator(),
                new TestBlackjackGame(new Deck(FIXED_CARDS))
        );

        // when
        app.run();
        String output = outContent.toString();

        // then - 초기 카드 배분 안내 메시지 포함
        assertThat(output).contains("영기, 라이에게 2장을 나누었습니다.");
    }

    @Test
    void 잘못된_참가자_입력_재시도() {
        // given - 첫 입력 숫자(invalid) → 재시도 → 정상 입력
        String input = "123\n영기,라이\nn\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Application app = new Application(
                new InputView(), new OutputView(),
                new Parser(), new Validator(),
                new TestBlackjackGame(new Deck(FIXED_CARDS))
        );

        // when
        app.run();
        String output = outContent.toString();

        // then - 에러 메시지 출력 후 정상 진행
        assertThat(output).contains("[ERROR]");
        assertThat(output).contains("영기", "라이");
    }
}
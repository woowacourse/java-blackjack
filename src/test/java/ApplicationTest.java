import static org.assertj.core.api.Assertions.assertThat;

import controller.BlackjackController;
import domain.card.OneDeckMaker;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import view.InputView;
import view.OutputView;

class ApplicationTest {

    public static Stream<Arguments> integrationTest() {
        return Stream.of(
                Arguments.of(
                        """
                                tion, jake
                                y
                                y
                                """, List.of(
                                "딜러와 tion, jake에게 2장을 나누었습니다.",
                                "딜러카드: K클로버",
                                "tion카드: K다이아몬드, K스페이드",
                                "jake카드: Q클로버, Q하트",
                                "tion는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                                "tion카드: K다이아몬드, K스페이드, Q다이아몬드",
                                "jake는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                                "jake카드: Q클로버, Q하트, Q스페이드",
                                "딜러카드: K클로버, K하트 - 결과: 20",
                                "tion카드: K다이아몬드, K스페이드, Q다이아몬드 - 결과: 30",
                                "jake카드: Q클로버, Q하트, Q스페이드 - 결과: 30",
                                "## 최종 승패",
                                "딜러: 2승",
                                "tion: 패",
                                "jake: 패")
                ));
    }

    @ParameterizedTest
    @MethodSource
    void integrationTest(String input, List<String> expected)
            throws Exception {
        ByteArrayInputStream inputArrayInputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BlackjackController blackjackController = new BlackjackController(
                new InputView(inputArrayInputStream), new OutputView(new PrintStream(byteArrayOutputStream)));

        blackjackController.start(new OneDeckMaker());
        String outputString = byteArrayOutputStream.toString();

        assertThat(outputString).contains(expected.toArray(new String[0]));
    }
}

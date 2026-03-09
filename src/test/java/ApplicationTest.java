import static org.assertj.core.api.Assertions.assertThat;

import controller.BlackjackController;
import domain.RandomValueGenerator;
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
                                "딜러카드: K스페이드",
                                "tion카드: K하트, K클로버",
                                "jake카드: Q스페이드, Q다이아몬드",
                                "tion는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                                "tion카드: K하트, K클로버, Q하트",
                                "jake는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                                "jake카드: Q스페이드, Q다이아몬드, Q클로버",
                                "딜러카드: K스페이드, K다이아몬드 - 결과: 20",
                                "tion카드: K하트, K클로버, Q하트 - 결과: 30",
                                "jake카드: Q스페이드, Q다이아몬드, Q클로버 - 결과: 30",
                                "## 최종 승패",
                                "딜러: 2승",
                                "tion: 패",
                                "jake: 패"),
                        (RandomValueGenerator) bound -> bound - 1
                ),
                Arguments.of(
                        """
                                tion, jake
                                n
                                n
                                """,
                        List.of(
                                "딜러카드: 7클로버, 7하트, 8다이아몬드 - 결과: 22",
                                "tion카드: 7다이아몬드, 7스페이드 - 결과: 14",
                                "jake카드: 8클로버, 8하트 - 결과: 16",
                                "딜러: 2패",
                                "tion: 승",
                                "jake: 승"),
                        (RandomValueGenerator) bound -> 24 // 7부터 제공
                ),
                Arguments.of(
                        """
                                tion, jake
                                y
                                y
                                """,
                        List.of(
                                "딜러카드: 7클로버, 7하트 - 결과: 14",
                                "tion카드: 7다이아몬드, 7스페이드, 8다이아몬드 - 결과: 22",
                                "jake카드: 8클로버, 8하트, 8스페이드 - 결과: 24",
                                "딜러: 2승",
                                "tion: 패",
                                "jake: 패"),
                        (RandomValueGenerator) bound -> 24 // 7부터 제공
                ));
    }

    @ParameterizedTest
    @MethodSource
    void integrationTest(String input, List<String> expected, RandomValueGenerator randomValueGenerator)
            throws Exception {
        ByteArrayInputStream inputArrayInputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BlackjackController blackjackController = new BlackjackController(
                new InputView(inputArrayInputStream), new OutputView(new PrintStream(byteArrayOutputStream)));

        blackjackController.start(randomValueGenerator);
        String outputString = byteArrayOutputStream.toString();

        assertThat(outputString).contains(expected.toArray(new String[0]));
    }
}

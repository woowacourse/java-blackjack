import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.sun.tools.javac.Main;
import controller.BlackjackController;
import domain.RandomValueGenerator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.ModuleLayer.Controller;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
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
                                "jake: 패")
                ),
                Arguments.of(
                        """
                                tion, jake
                                n
                                n
                                """,
                        List.of(
                                "딜러: 2무",
                                "tion: 무",
                                "jake: 무")
                ));
    }

    @ParameterizedTest
    @MethodSource
    void integrationTest(String input, List<String> expected) throws Exception {
        ByteArrayInputStream inputArrayInputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        RandomValueGenerator randomValueGenerator = (n) -> n - 1;
        BlackjackController blackjackController = new BlackjackController(
                new InputView(inputArrayInputStream), new OutputView(new PrintStream(byteArrayOutputStream)),
                randomValueGenerator);

        blackjackController.start();

        String outputString = byteArrayOutputStream.toString();

        for (String s : expected) {
            assertThat(outputString).contains(s);
        }
    }
}
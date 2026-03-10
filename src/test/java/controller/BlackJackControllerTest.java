package controller;
import domain.Deck;
import factory.CardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.*;

public class BlackJackControllerTest {

    private ByteArrayOutputStream outputStreamCaptor;

    private void systemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    private String getOutput() {
        return outputStreamCaptor.toString();
    }

    @Test
    void 게임_전체_실행이_정상적으로_동작하는_경우를_테스트한다(){
        Supplier<Deck> testDeckSupplier = () -> new Deck(CardFactory.createDeck());
        BlackJackController controller = new BlackJackController(testDeckSupplier);

        systemIn("시오,봉구스\ny\nn\ny\nn");
        controller.run();

        String output = getOutput();
        assertThat(output).contains("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
                .contains("딜러와 시오, 봉구스에게 2장을 나누었습니다.")
                .contains("딜러카드 : 5하트")
                .contains("시오카드 : A하트, 2하트")
                .contains("봉구스카드 : 3하트, 4하트")
                .contains("시오는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                .contains("시오카드 : A하트, 2하트, 7하트")
                .contains("시오는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                .contains("봉구스는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                .contains("봉구스카드 : 3하트, 4하트, 8하트")
                .contains("봉구스는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                .contains("딜러는 16이하라 한장의 카드를 더 받았습니다.")
                .contains("딜러카드 : 5하트, 6하트, 9하트 - 결과: 20")
                .contains("시오카드 : A하트, 2하트, 7하트 - 결과: 20")
                .contains("봉구스카드 : 3하트, 4하트, 8하트 - 결과: 15")
                .contains("## 최종 승패")
                .contains("딜러: 1승 0패")
                .contains("시오: 무")
                .contains("봉구스: 패");
    }
}



package blackjack.controller;

import blackjack.view.InputView;
import blackjack.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class BlackjackControllerTest {

    @Test
    @DisplayName("컨트롤러가 잘돌아가는 지 테스트")
    void run1() {
        String input ="a,b\n1000\n1000\nn\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());


        System.setIn(in);
        BlackjackController controller = new BlackjackController(new InputView(), new OutputView());
        controller.run();
    }

    @Test
    @DisplayName("잘못된 이름이 들어갈 때 재입력 하는지 테스트")
    void run2() {
        String input ="!,b\na,b\n1000\n1000\nn\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());


        System.setIn(in);
        BlackjackController controller = new BlackjackController(new InputView(), new OutputView());
        controller.run();
    }

    @Test
    @DisplayName("잘못된 명령어가 들어갈 때 재입력 하는지 테스트")
    void run3() {
        String input ="a,b\n1000\n1000\nm\nn\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());


        System.setIn(in);
        BlackjackController controller = new BlackjackController(new InputView(), new OutputView());
        controller.run();
    }

    @Test
    @DisplayName("잘못된 배팅금액이 들어갈 때 재입력 하는지 테스트")
    void run4() {
        String input ="a,b\n100\n1000\n1000\nm\nn\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());


        System.setIn(in);
        BlackjackController controller = new BlackjackController(new InputView(), new OutputView());
        controller.run();
    }
}

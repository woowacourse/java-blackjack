package blackjack.application;

import blackjack.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackControllerTest extends BlackjackTest {

    @Test
    void 전체_시나리오() {
        run("pobi,neo", "10000", "20000", "y", "n", "n");

        assertThat(output())
                .contains("pobi의 배팅 금액은?")
                .contains("neo의 배팅 금액은?")
                .contains("딜러와 pobi, neo에게 2장을 나누었습니다.")
                .contains("pobi카드: K클로버, 10클로버, 7클로버")
                .contains("neo카드: Q클로버, 9클로버")
                .contains("## 최종 승패")
                .contains("딜러: 1승 1패")
                .contains("pobi: 패")
                .contains("neo: 승");
    }

    @Override
    protected void runMain() {
        new BlackjackController(new InputView(), new OutputView())
                .run(Collections::reverse);
    }
}

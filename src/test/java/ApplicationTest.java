import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    @DisplayName("블랙잭 게임 실행 결과 출력")
    @Test
    void testBlackjackGameResult() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,jason", "y", "n", "n");
                assertThat(output()).contains(
                    "딜러와 pobi, jason에게 2장을 나누었습니다.",
                    "딜러: 3다이아몬드",
                    "pobi카드: 2하트, 8스페이드",
                    "jason카드: 7클로버, K스페이드",
                    "pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                    "pobi카드: 2하트, 8스페이드, A클로버",
                    "pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                    "jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
                    "딜러는 16이하라 한장의 카드를 더 받았습니다.",
                    "딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20",
                    "pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21",
                    "jason카드: 7클로버, K스페이드 - 결과: 17",
                    "## 최종 승패",
                    "딜러: 1승 1패",
                    "pobi: 승",
                    "jason: 패"
                );
            },
            2, 2,
            8, 3,
            1, 1,
            7, 0,
            6, 3,
            11, 0,
            0, 3,
            7, 2
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}

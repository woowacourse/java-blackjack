package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Deck;
import blackjack.domain.deck.MyShuffle;
import blackjack.view.input.FakeInputView;
import blackjack.view.output.FakeOutputView;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackControllerTest {

    @Test
    void 기능_테스트() {
        // given
        FakeInputView inputView = new FakeInputView(
                List.of("달수", "제리", "밀란", "한다"),
                List.of(1000, 2000, 3000, 4000),
                List.of(false, false, false, false)
        );
        FakeOutputView outputView = new FakeOutputView();
        Deck deck = Deck.createWithShuffled(new MyShuffle());
        BlackjackController controller = new BlackjackController(inputView, outputView, deck);

        // when
        controller.run();

        // then
        assertThat(outputView.logs()).contains(
                "딜러와 달수,제리,밀란,한다에게 2장을 나누었습니다.",
                "딜러카드: 5다이아몬드",
                "달수카드: A다이아몬드,6다이아몬드",
                "제리카드: 2다이아몬드,7다이아몬드",
                "밀란카드: 3다이아몬드,8다이아몬드",
                "한다카드: 4다이아몬드,9다이아몬드",
                "딜러는 16이하라 한장의 카드를 더 받았습니다.",
                "딜러카드: 5다이아몬드,10다이아몬드,J다이아몬드 - 결과: 25",
                "달수카드: A다이아몬드,6다이아몬드 - 결과: 17",
                "제리카드: 2다이아몬드,7다이아몬드 - 결과: 9",
                "밀란카드: 3다이아몬드,8다이아몬드 - 결과: 11",
                "한다카드: 4다이아몬드,9다이아몬드 - 결과: 13",
                "## 최종 수익",
                "딜러: -10000",
                "달수: 1000",
                "제리: 2000",
                "밀란: 3000",
                "한다: 4000"
        );
    }

}

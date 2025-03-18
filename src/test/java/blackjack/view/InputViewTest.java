package blackjack.view;

import blackjack.test_util.ReaderStub;
import blackjack.test_util.WriterStub;
import blackjack.util.RetryHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class InputViewTest {

    @Test
    void 사용자에게_이름들을_입력받을_수_있다() {
        // given
        final WriterStub writerStub = new WriterStub();
        final InputView inputView = new InputView(writerStub, new ReaderStub("pobi,jason"), new RetryHandler(new OutputView(writerStub)));

        // when
        final List<String> playerNames = inputView.getPlayerNames();

        // then
        assertAll(
                () -> assertThat(playerNames).containsExactly("pobi", "jason"),
                () -> assertThat(writerStub.getOutputs()).containsExactly("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        );
    }

    @Test
    void 사용자에게_카드를_더_받을_지_입력받을_수_있다() {
        // given
        final WriterStub writerStub = new WriterStub();
        final InputView inputView = new InputView(writerStub, new ReaderStub("y"), new RetryHandler(new OutputView(writerStub)));

        // when
        final boolean decision = inputView.getAddingCardDecision("pobi");

        // then
        assertAll(
                () -> assertThat(decision).isTrue(),
                () -> assertThat(writerStub.getOutputs()).containsExactly("pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        );
    }

    @Test
    void 사용자에게_카드를_더_받지_않을지_입력받을_수_있다() {
        // given
        final WriterStub writerStub = new WriterStub();
        final InputView inputView = new InputView(writerStub, new ReaderStub("n"), new RetryHandler(new OutputView(writerStub)));

        // when
        final boolean decision = inputView.getAddingCardDecision("pobi");

        // then
        assertAll(
                () -> assertThat(decision).isFalse(),
                () -> assertThat(writerStub.getOutputs()).containsExactly("pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        );
    }

    @Test
    void 사용자에게_카드를_더_받을_지_입력받을_때_y나_n이_아니면_재시도한다() {
        // given
        final WriterStub writerStub = new WriterStub();
        final InputView inputView = new InputView(writerStub, new ReaderStub("Y"), new RetryHandler(new OutputView(writerStub)));

        // when
        assertThatThrownBy(() -> inputView.getAddingCardDecision("pobi"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 재시도 횟수를 초과했습니다.");
        assertThat(writerStub.getOutputs()).contains("[ERROR] y나 n을 입력하세요.");
    }
}

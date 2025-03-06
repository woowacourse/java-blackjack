package blackjack.controller;

import blackjack.testutil.ReaderStub;
import blackjack.testutil.WriterStub;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlackjackControllerTest {
    
    @Test
    void hello() {
        // given
        final WriterStub writerStub = new WriterStub();
        final ReaderStub readerStub = new ReaderStub(
                "dompoo,may",
                "n",
                "n"
        );
        
        final BlackjackController blackjackController = new BlackjackController(
                new InputView(writerStub, readerStub),
                new OutputView(writerStub)
        );
        
        // when
        blackjackController.run();
        
        // then
        assertThat(writerStub.getOutputs()).contains(
                "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)",
                "\n딜러와 dompoo, may에게 2장을 나누었습니다.",
                "\n이제 dompoo가 카드를 더 받는 순서입니다.",
                "\n이제 may가 카드를 더 받는 순서입니다.",
                "\n<최종 승패>"
        );
    }
    
}

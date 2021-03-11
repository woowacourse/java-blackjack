package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Cards;
import blackjack.domain.card.PlayerCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {
    @DisplayName("stay로 상태변환 테스트")
    @Test
    void stay() {
        Finished finished = new Blackjack(new PlayerCards());
        assertThatThrownBy(() -> finished.stay())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("카드뽑기 테스트")
    @Test
    void draw() {
        Finished finished = new Blackjack(new PlayerCards());
        assertThatThrownBy(() -> finished.draw(Cards.getInstance().draw()))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("종료확인 테스트")
    @Test
    void isFinished() {
        Finished finished = new Blackjack(new PlayerCards());
        assertThat(finished.isFinished()).isTrue();
    }
}
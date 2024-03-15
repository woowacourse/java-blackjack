package domain.state.fininsh;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.state.Hands;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {
    private final Finished bust = new Bust(new Hands(List.of()));

    @Test
    @DisplayName("게임이 끝난 상태에서 카드를 뽑으면 예외가 발생한다")
    void draw() {
        assertThatCode(() -> bust.add(new Card(Rank.TEN, Suit.CLUBS))).isInstanceOf(
                UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("게임이 끝난 상태에서 스탠드를 하면 예외가 발생한다")
    void stand() {
        assertThatCode(bust::stand).isInstanceOf(
                UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("게임이 끝난 상태는 자신의 상태가 끝났음을 알릴 수 있다")
    void isFinish() {
        assertThat(bust.isFinished()).isTrue();
    }
}

package domain.state.fininsh;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.Hands;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {
    private final Finished bust = new Bust(new Hands(List.of()));

    @Test
    @DisplayName("게임이 끝난 상태에서 카드를 뽑으면 예외가 발생한다")
    void draw() {
        assertThatThrownBy(() -> bust.add(new Card(Rank.TEN, Suit.CLUBS))).isInstanceOf(
                UnsupportedOperationException.class).hasMessage("게임이 끝난 상태에서 카드를 더이상 뽑을 수 없습니다");
    }

    @Test
    @DisplayName("게임이 끝난 상태에서 스탠드를 하면 예외가 발생한다")
    void stand() {
        assertThatThrownBy(bust::stand).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 끝난 상태에서 스탠드를 할 수 없습니다");
    }

    @Test
    @DisplayName("게임이 끝난 상태는 자신의 상태가 끝났음을 알 수 있다")
    void isFinish() {
        assertThat(bust.isFinished()).isTrue();
    }

}

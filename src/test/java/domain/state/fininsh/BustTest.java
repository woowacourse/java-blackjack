package domain.state.fininsh;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    @Test
    @DisplayName("게임이 끝난 상태에서 카드를 뽑으면 예외가 발생한다")
    void draw() {
        final State bust = new Bust();
        assertThatCode(() -> bust.draw(new Card(Rank.TEN, Suit.CLUBS))).isInstanceOf(
                UnsupportedOperationException.class);
    }

    @Test
    void earningRate() {
    }
}

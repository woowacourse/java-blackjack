package domain.state.run;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.Hands;
import domain.card.Rank;
import domain.card.Suit;
import domain.state.State;
import domain.state.fininsh.Stand;
import java.util.List;
import org.junit.jupiter.api.Test;

class RunningTest {
    @Test
    void compareWith() {
        final State hit = new Hit(new Hands(List.of(new Card(Rank.TEN, Suit.CLUBS))));
        final State stand = new Stand(new Hands(List.of(new Card(Rank.TEN, Suit.CLUBS))));
        assertThatThrownBy(() -> hit.compareWith(stand))
                .isInstanceOf(IllegalCallerException.class)
                .hasMessage("상태가 실행 중이어서 비교가 불가능합니다");
    }
}

package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    @DisplayName("턴 클래스 생성 확인")
    void startTurn() {
        Turn turn = new Turn(3);
        assertThat(turn).isInstanceOf(Turn.class);
    }

    @Test
    @DisplayName("next 시 움직이는지 확인")
    void next() {
        Turn turn = new Turn(3);
        turn.next();
        Turn compareTurn = new Turn(3);
        compareTurn.next();
        assertThat(turn).isEqualTo(compareTurn);
    }

    @Test
    @DisplayName("모든 턴이 끝났는지 확인")
    void isEndOfTurn() {
        Turn turn = new Turn(0);
        turn.next();
        assertThat(turn.isEndOfTurn()).isFalse();
    }
}

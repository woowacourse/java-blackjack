package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    @Test
    @DisplayName("턴이 종료되지 않은 경우에 카드의 합을 계산하려하면 예외를 발생시킨다.")
    void calculateResultScoreExceptionByNotEndTurn() {
        final Player player = new Player("user", new ArrayList<>());
        assertThatThrownBy(player::calculateScore)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되지 않아 카드의 합을 반환할 수 없습니다.");
    }
}

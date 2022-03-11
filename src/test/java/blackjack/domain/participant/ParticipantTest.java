package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParticipantTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("플레이어의 이름에 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull(String input) {
        assertThatThrownBy(() -> Player.newInstance(input, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름에는 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어의 이름에 공백이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByEmpty(String input) {
        assertThatThrownBy(() -> Player.newInstance(input, new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름에는 공백이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("턴이 종료되지 않은 경우에 카드의 합을 계산하려하면 예외를 발생시킨다.")
    void calculateResultScoreExceptionByNotEndTurn() {
        final Player player = Player.newInstance("user", new ArrayList<>());
        assertThatThrownBy(player::calculateScore)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되지 않아 카드의 합을 반환할 수 없습니다.");
    }
}

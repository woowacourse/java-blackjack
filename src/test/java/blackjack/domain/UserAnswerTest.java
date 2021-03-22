package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserAnswerTest {

    @ParameterizedTest(name = "카드 요청의 입력값은 null이 될 수 없습니다.")
    @NullSource
    void nullInputTest(String input) {
        assertThatThrownBy(
                () -> UserAnswer.getUserAnswer(input)).isInstanceOf(NullPointerException.class)
                .hasMessage("카드 요청 입력값은 null일 수 없습니다.");
    }

    @ParameterizedTest(name = "카드 요청의 입력값은 빈 값이 될 수 없습니다.")
    @EmptySource
    void emptyInputTest(String input) {
        assertThatThrownBy(
                () -> UserAnswer.getUserAnswer(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 요청 입력값은 빈값이 될 수 없습니다.");
    }

    @Test
    @DisplayName("카드 요청의 입력값 똑바로 입력 됐다.")
    void validateInputTest() {
        UserAnswer hit = UserAnswer.getUserAnswer("y");
        UserAnswer stay = UserAnswer.getUserAnswer("n");

        assertThat(hit).isEqualTo(UserAnswer.HIT);
        assertThat(stay).isEqualTo(UserAnswer.STAY);
    }

    @Test
    @DisplayName("카드 요청 입력값으로 올바르지 못한값을 입력했다")
    void invalidInputTest() {
        assertThatThrownBy(() -> {
            UserAnswer.getUserAnswer("이건 잘못된 입력이다.");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("요청은 " + UserAnswer.HIT.getAnswer() + "또는 " + UserAnswer.STAY.getAnswer() + "이어야 합니다.");
    }

    @Test
    @DisplayName("유저의 대답이 STAY 인지 아닌지 판단한다.")
    void isStayTest() {
        UserAnswer stay = UserAnswer.STAY;
        UserAnswer hit = UserAnswer.HIT;

        assertThat(stay.isStay()).isTrue();
        assertThat(hit.isStay()).isFalse();
    }
}

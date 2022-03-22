package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChoiceTest {

    @Test
    @DisplayName("y를 입력했을 때 YES 반환")
    void choiceFrom_y() {
        assertThat(Choice.from("y")).isEqualTo(Choice.YES);
    }

    @Test
    @DisplayName("n를 입력했을 때 NO 반환")
    void choiceFrom_n() {
        assertThat(Choice.from("n")).isEqualTo(Choice.NO);
    }

}


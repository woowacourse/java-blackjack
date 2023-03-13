package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    @Test
    @DisplayName("y 를 입력하면 MORE_CARD 를 반환한다.")
    void returnMoreCard() {
        assertThat(Answer.from("y")).isEqualTo(Answer.MORE_CARD);
    }

    @Test
    @DisplayName("n 을 입력하면 CARD_STOP 을 반환한다.")
    void returnCardStop() {
        assertThat(Answer.from("n")).isEqualTo(Answer.CARD_STOP);
    }

    @Test
    @DisplayName("y 나 n 이 아니면 예외를 반환한다.")
    void returnAnswerFail() {
        assertThatThrownBy(() -> Answer.from("q"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("y 나 n 만을 입력해주세요.");
    }

    @Test
    @DisplayName("카드를 더 받는 상태인지 확인한다.")
    void isMoreCard() {
        assertThat(Answer.MORE_CARD.isMoreCard()).isTrue();
    }

}

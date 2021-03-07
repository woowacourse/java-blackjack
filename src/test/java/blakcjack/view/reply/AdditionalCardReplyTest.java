package blakcjack.view.reply;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AdditionalCardReplyTest {
    @DisplayName("객체 생성 성공")
    @Test
    void create_success() {
        assertThat(AdditionalCardReply.from("y"))
                .isEqualTo(AdditionalCardReply.GO);

        assertThat(AdditionalCardReply.from("Y"))
                .isEqualTo(AdditionalCardReply.STOP);
    }

    @DisplayName("카드 객체 생성 실패")
    @Test
    void create_fail() {
        assertThatThrownBy(() -> AdditionalCardReply.from(null))
                .isInstanceOf(InvalidReplyException.class);

        assertThatThrownBy(() -> AdditionalCardReply.from("a"))
                .isInstanceOf(InvalidReplyException.class);
    }
}

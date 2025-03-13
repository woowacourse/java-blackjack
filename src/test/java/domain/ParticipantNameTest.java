package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantNameTest {

    @Test
    void 이름이_같으면_true를_반환한다() {
        // given
        ParticipantName name = ParticipantName.nameOf("루키");
        ParticipantName name2 = ParticipantName.nameOf("루키");

        // when & then
        assertThat(name.isMatch(name2)).isTrue();
    }

    @Test
    void 이름이_null이면_예외가_발생한다() {
        // given
        String name = null;

        // when & then
        assertThatThrownBy(() -> ParticipantName.nameOf(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임은 공백일 수 없습니다");
    }

    @Test
    void 이름이_공백이면_예외가_발생한다() {
        // given
        String name = "";

        // when & then
        assertThatThrownBy(() -> ParticipantName.nameOf(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임은 공백일 수 없습니다");
    }
}

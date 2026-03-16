package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknamesTest {

    @Test
    @DisplayName("중복된 닉네임이 존재하면 예외가 발생한다.")
    void duplicateNicknameException() {
        // given
        List<String> nicknames = List.of("boye", "boye", "sumin");

        // when & then
        assertThatThrownBy(() -> Nicknames.from(nicknames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 닉네임이 존재합니다.");
    }
}

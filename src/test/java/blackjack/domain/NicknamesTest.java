package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknamesTest {

    @Test
    @DisplayName("플레이어 이름이 없으면 예외가 발생한다.")
    void emptyNicknameException() {
        // given
        List<String> nicknames = List.of();

        // when & then
        assertThatThrownBy(() -> Nicknames.from(nicknames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어 이름은 1명 이상 입력해야 합니다.");
    }

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

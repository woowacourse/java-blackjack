package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.player.Name;

class NameTest {
    @DisplayName("이름으로 공백이 입력되면 예외를 던지는지 확인한다")
    @Test
    void Should_ThrowException_When_BlankName() {
        String blankName = "";
        assertThatThrownBy(() -> new Name(blankName))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름으로 앞뒤 공백이 제거되는지 확인한다")
    @Test
    void Should_RemoveFrontEndBackBlank_When_WithBlankName() {
        String withBlankName = "  na me  ";
        assertThat(new Name(withBlankName).getName())
            .isEqualTo(withBlankName.trim());
    }

    @DisplayName("이름 앞 뒤 공백이 제거된 길이가 15글자 초과하면 예외를 던지는지 확인한다")
    @Test
    void Should_ThrowException_When_NameLengthOverRange() {
        String overRangeName = " 123456 78910 1112131415 ";
        assertThatThrownBy(() -> new Name(overRangeName))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}

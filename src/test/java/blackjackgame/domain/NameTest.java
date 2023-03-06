package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @DisplayName("이름이 올바르게 입력되었을 때 생성되는 Name 클래스가 입력된 이름과 똑같은 값을 가지고 있는지 확인한다.")
    @Test
    void Should_ValidNameClassHasSameName_When_ValidName() {
        String validName = "pobi";
        Name pobi = new Name(validName);
        assertThat(pobi.getName()).isEqualTo(validName);
    }

    @DisplayName("이름으로 공백이 입력되면 예외를 던지는지 확인한다")
    @Test
    void Should_ThrowException_When_BlankName() {
        String blankName = "";
        assertThatThrownBy(() -> new Name(blankName))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름 앞 뒤 공백이 제거된 길이가 15글자 초과하면 예외를 던지는지 확인한다")
    @Test
    void Should_ThrowException_When_NameLengthOverRange() {
        String overRangeName = " 123456 78910 1112131415 ";
        assertThatThrownBy(() -> new Name(overRangeName))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}

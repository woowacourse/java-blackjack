package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {
    @DisplayName("유효한 이름 생성 테스트")
    @Test
    void testCreateNameTest() {
        Name name1 = Name.from("이름");
        Name name2 = Name.from("pobi");
        Name name3 = Name.from("jason");

        assertThat(name1).isEqualTo(Name.from("이름"));
        assertThat(name2).isEqualTo(Name.from("pobi"));
        assertThat(name3).isEqualTo(Name.from("jason"));
    }

    @DisplayName("유효하지 않은 이름이 들어온 경우 예외를 발생시킨다.")
    @Test
    void testNotValidName() {
        String wrongName1 = "";

        assertThatThrownBy(() -> Name.from(wrongName1)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 값은 입력될 수 없습니다.");
    }
}

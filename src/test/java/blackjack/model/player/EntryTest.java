package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EntryTest {

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @Test
    void build_exception_blank() {
        assertThatThrownBy(() -> new Entry(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @DisplayName("이름이 15자를 초과하면 예외가 발생한다")
    @Test
    void build_exception_max_length() {
        assertThatThrownBy(() -> new Entry("아차산메이웨더미래의챔피언리버굿"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 15자 이하로 입력해주세요.");
    }

    @ParameterizedTest(name = "이름에 숫자가 포함되면 예외가 발생한다 : {0}")
    @ValueSource(strings = {"아차산메2웨더", "4키", "19951114", "2"})
    void build_exception_contains_number(String name) {
        assertThatThrownBy(() -> new Entry(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름에 숫자는 포함될 수 없습니다.");
    }

    @ParameterizedTest(name = "이름에 기호가 포함되면 예외가 발생한다 : {0}")
    @ValueSource(strings = {"#$", "포^키입니다크킄", "?", "#리버입니다크크킄", "\\"})
    void build_exception_contains_sign(String name) {
        assertThatThrownBy(() -> new Entry(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름에 기호는 포함될 수 없습니다.");
    }

    @DisplayName("Builder를 통해 Entry를 생성한다")
    @Test
    void build_entry() {
        Player liver = new Entry("아차산메이웨더미래의챔피언리버");

        assertThat(liver).isInstanceOf(Entry.class);
    }
}

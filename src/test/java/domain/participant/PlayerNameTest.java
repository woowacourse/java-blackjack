package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameTest {

    @DisplayName("null이 들어오면 예외가 발생한다")
    @Test
    void null이면_예외가_발생한다() {
        assertThatThrownBy(() -> new PlayerName(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("공백이 들어오면 예외가 발생한다")
    @Test
    void 공백이면_예외가_발생한다() {
        assertThatThrownBy(() -> new PlayerName(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 이름이면 동등하다")
    @Test
    void 같은_이름이면_동등하다() {
        PlayerName name1 = new PlayerName("pobi");
        PlayerName name2 = new PlayerName("pobi");
        assertThat(name1).isEqualTo(name2);
    }

    @DisplayName("다른 이름이면 동등하지 않다")
    @Test
    void 다른_이름이면_동등하지_않다() {
        PlayerName name1 = new PlayerName("pobi");
        PlayerName name2 = new PlayerName("jason");
        assertThat(name1).isNotEqualTo(name2);
    }
}

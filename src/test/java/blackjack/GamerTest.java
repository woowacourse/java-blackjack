package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.player.Gamer;
import blackjack.model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GamerTest {

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @Test
    void build_exception_blank() {
        assertThatThrownBy(() -> new Gamer(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @DisplayName("이름이 15자를 초과하면 예외가 발생한다")
    @Test
    void build_exception_max_length() {
        assertThatThrownBy(() -> new Gamer("아차산메이웨더미래의챔피언리버굿"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 15자 이하로 입력해주세요.");
    }

    @DisplayName("Builder를 통해 Entry를 생성한다")
    @Test
    void build_entry() {
        Player liver = new Gamer("아차산메이웨더미래의챔피언리버");

        assertThat(liver).isInstanceOf(Gamer.class);
    }
}

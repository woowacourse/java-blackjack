package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.player.Gamers;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    @DisplayName("중복된 이름이 있을 때 예외가 발생한다.")
    @Test
    void nextEntry_exception_duplicate_name() {
        List<String> names = List.of("포키", "리버", "포키");

        assertThatThrownBy(() -> Gamers.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복되는 이름이 있습니다.");
    }

    @DisplayName("포키와 리버가 있을 때, 리버의 턴을 시작한다")
    @Test
    void nextEntry_reaver() {
        List<String> names = List.of("포키", "리버");
        Gamers gamers = Gamers.from(names);

        gamers.nextGamer();

        assertThat(gamers.getCurrentValue().getName()).isEqualTo(names.get(1));
    }
}

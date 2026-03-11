package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayersTest {
    @Test
    void 중복된_플레이어_이름은_등록할_수_없다() {
        // given
        List<String> duplicatedNames = List.of("jeje", "mingu", "jeje");

        // when & then
        Assertions.assertThatThrownBy(() -> Players.from(duplicatedNames)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 닉네임이_중복되지_않으면_정상적으로_생성되어야_한다() {
        // given
        List<String> uniqueNames = List.of("jeje", "mingu", "minseo");

        // when & then
        Assertions.assertThat(Players.from(uniqueNames).getPlayers()).hasSize(3);
    }
}

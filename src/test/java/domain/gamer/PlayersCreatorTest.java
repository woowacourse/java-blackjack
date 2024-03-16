package domain.gamer;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersCreatorTest {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;

    @DisplayName("플레이어의 인원이 최소 2명보다 적을 경우 Error를 throw 한다.")
    @Test
    void isNotOverPossiblePlayerRange() {
        List<String> names = List.of("hogi");

        Assertions.assertThatThrownBy(() -> new PlayersCreator().create(names, List.of(10000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
    }

    @DisplayName("플레이어의 인원이 최소 8명보다 많을 경우 Error를 throw 한다.")
    @Test
    void isOverPossiblePlayerRange() {
        List<String> names = List.of("pola", "ato", "kaki", "hogi", "pobi", "jazz", "lisz", "takan", "siso", "hoho");

        Assertions.assertThatThrownBy(() -> new PlayersCreator().create(names, List.of(10000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
    }

    @DisplayName("플레이어의 이름이 중복이 되는 경우 에러를 반환한다.")
    @Test
    void isPlayersHasDuplicateName() {
        List<String> names = List.of("pola", "pola", "hogi");

        Assertions.assertThatThrownBy(() -> new PlayersCreator().create(names, List.of(10000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자는 중복된 이름을 가질 수 없습니다.");
    }
}

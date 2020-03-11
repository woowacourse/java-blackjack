package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PlayersTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력된 이름 리스트의 null, empty 예외처리")
    void nullAndEmptyTest(List<String> input) {
        assertThatThrownBy(() -> new Players(input)).
            isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("getCardsByName 존재하지 않는 유저이름 입력 예외처리")
    void getCardsByNameNonExistException() {
        Players players = new Players(Arrays.asList("오렌지", "히히"));
        assertThatThrownBy(() -> players.getCardsByName("오렌지2"))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("getCardsByName 중복되는 유저이름 입력 예외처리")
    void getCardsByNameDuplicationException() {
        Players players = new Players(Arrays.asList("오렌지", "오렌지"));
        assertThatThrownBy(() -> players.getCardsByName("오렌지"))
            .isInstanceOf(IllegalStateException.class);
    }
}

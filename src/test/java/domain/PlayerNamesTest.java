package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerNamesTest {

    @Test
    void player는_5명까지_참여_가능하다() {
        //given, when
        List<String> playerNames = List.of("judy", "jude", "pobi", "lio", "neo", "joan");

        //then
        assertThatThrownBy(() -> new PlayerNames(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void player는_중복된_이름을_가질_수_없다() {
        //given, when
        List<String> playerNames = List.of("judy", "judy");

        //then
        assertThatThrownBy(() -> new PlayerNames(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_테스트() {
        //given, when, then
        assertDoesNotThrow(() -> new PlayerNames(List.of("judy", "brown")));
    }
}

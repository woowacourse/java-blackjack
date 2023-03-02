package domain;


import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayersTest {

    @Test
    void player는_5명까지_참여_가능하다() {
        //given,//when
        List<String> playerNames = List.of("judy", "jude", "pobi", "lio", "neo", "joan");

        //then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레어이는 5명까지 참가 가능합니다.");
    }

    @Test
    void player는_중복된_이름을_가질_수_없다() {
        //given, when
        List<String> playerNames = List.of("judy", "judy");
        //then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레이어 이름은 중복일 수 없습니다.");
    }
}
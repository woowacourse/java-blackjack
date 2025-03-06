package domain;

import java.util.List;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void 인원_수가_5명_초과이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc", "dd", "ee", "ff");

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 인원_수가_0명이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of();

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이름의_플레이어가_여러_명_있으면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "aa");

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

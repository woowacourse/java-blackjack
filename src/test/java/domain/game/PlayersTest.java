package domain.game;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 인원_수가_5명_초과이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc", "dd", "ee", "ff");
        List<Integer> playerBettingAmounts = List.of(1, 2, 3, 4, 5, 6);

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames, playerBettingAmounts))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 인원_수가_0명이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of();
        List<Integer> playerBettingAmounts = List.of();

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames, playerBettingAmounts))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이름의_플레이어가_여러_명_있으면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "aa");
        List<Integer> playerBettingAmounts = List.of(1000, 2000);

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames, playerBettingAmounts))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어의_이름을_가져온다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc");
        List<Integer> playerBettingAmount = List.of(1000, 2000, 3000);
        Players players = new Players(playerNames, playerBettingAmount);

        //when
        List<String> actual = players.getAllPlayerNames();

        //then
        Assertions.assertThat(actual).containsExactlyElementsOf(playerNames);
    }
}

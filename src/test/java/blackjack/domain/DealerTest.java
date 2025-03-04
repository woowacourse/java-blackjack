package blackjack.domain;

import blackjack.domian.Dealer;
import blackjack.domian.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 플레이어_수가_최대_10명을_넘으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player(), new Player(), new Player(), new Player(),
                new Player(), new Player(), new Player(), new Player(),
                new Player(), new Player(), new Player()
        );

        //when & then
        Assertions.assertThatThrownBy(() -> new Dealer(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최대 10명입니다.");
    }

    @Test
    void 플레이어_수가_최소_2명을_넘지_않으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player()
        );

        //when & then
        Assertions.assertThatThrownBy(() -> new Dealer(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최소 2명입니다.");
    }
}

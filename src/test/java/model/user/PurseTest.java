package model.user;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PurseTest {

    @Test
    @DisplayName("지갑을 생성할 때, 기본적으로 0원을 들고 있게 한다.")
    void whenCreatePurse_thenHaveZeroMoney() {
        // given
        final Player bebe = new Player("bebe");
        final Player ethan = new Player("ethan");
        final List<Player> players = List.of(bebe, ethan);

        // when
        final Purse purse = Purse.create(players);

        // then
        assertThat(purse)
                .extracting("purses", InstanceOfAssertFactories.map(Player.class, Money.class))
                .containsExactly(Map.entry(bebe, Money.zero()), Map.entry(ethan, Money.zero()));
    }
}

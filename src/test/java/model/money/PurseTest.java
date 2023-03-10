package model.money;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import model.user.Player;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurseTest {

    private Player bebe;
    private Player ethan;
    private Purse purse;

    @BeforeEach
    void init() {
        bebe = new Player("bebe");
        ethan = new Player("ethan");
        final List<Player> players = List.of(bebe, ethan);

        purse = Purse.create(players);
    }

    @Test
    @DisplayName("지갑을 생성할 때, 기본적으로 0원을 들고 있게 한다.")
    void whenCreatePurse_thenHaveZeroMoney() {
        assertThat(purse)
                .extracting("purses", InstanceOfAssertFactories.map(Player.class, Money.class))
                .containsExactly(Map.entry(bebe, Money.zero()), Map.entry(ethan, Money.zero()));
    }

    @Test
    @DisplayName("지갑에 돈을 추가할 수 있다.")
    void whenAddMoney_thenSuccess() {
        // when
        purse.addMoney(bebe, new Money(10_000));

        // then
        assertThat(purse.getMoney(bebe)).isEqualTo(new Money(10_000));
    }
}

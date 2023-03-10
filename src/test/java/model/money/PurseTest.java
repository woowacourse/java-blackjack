package model.money;

import static model.card.CardFixture.DIAMOND_ACE;
import static model.card.CardFixture.DIAMOND_JACK;
import static model.card.CardFixture.DIAMOND_KING;
import static model.card.CardFixture.DIAMOND_NINE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import model.user.Dealer;
import model.user.Player;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurseTest {

    private Player bebe;
    private Player ethan;
    private Dealer dealer;

    private Purse purse;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        bebe = new Player("bebe");
        ethan = new Player("ethan");
        final List<Player> players = List.of(bebe, ethan);

        purse = Purse.create(players);
    }

    @Test
    @DisplayName("지갑을 생성할 때, 기본적으로 0원을 들고 있게 한다.")
    void whenCreatePurse_thenHaveZeroMoney() {
        assertThat(purse)
                .extracting("purses", InstanceOfAssertFactories.map(Player.class, Bet.class))
                .containsExactly(Map.entry(bebe, Bet.zero()), Map.entry(ethan, Bet.zero()));
    }

    @Test
    @DisplayName("지갑에 돈을 추가할 수 있다.")
    void whenAddMoney_thenSuccess() {
        // when
        purse.addMoney(bebe, new Bet(10_000));

        // then
        assertThat(purse.getMoney(bebe)).isEqualTo(new Bet(10_000));
    }

    @Test
    @DisplayName("플레이어와 블랙잭이면 돈이 1.5배로 바뀐다.")
    void whenBlackJack_thenChangeMultipleMoney() {
        // given
        purse.addMoney(bebe, new Bet(10_000));

        bebe.receiveCard(DIAMOND_KING);
        bebe.receiveCard(DIAMOND_ACE);

        dealer.receiveCard(DIAMOND_JACK);
        dealer.receiveCard(DIAMOND_NINE);

        // when
        purse.calculateMoney(bebe, dealer);

        // then
        assertThat(purse.getMoney(bebe)).isEqualTo(new Bet(15_000));
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이면 돈을 돌려받는다.")
    void whenDealerAndPlayerBothBlackJack_thenReturnMoney() {
        // given
        purse.addMoney(bebe, new Bet(10_000));

        bebe.receiveCard(DIAMOND_KING);
        bebe.receiveCard(DIAMOND_ACE);

        dealer.receiveCard(DIAMOND_JACK);
        dealer.receiveCard(DIAMOND_ACE);

        // when
        purse.calculateMoney(bebe, dealer);

        // then
        assertThat(purse.getMoney(bebe)).isEqualTo(new Bet(0));
    }
}

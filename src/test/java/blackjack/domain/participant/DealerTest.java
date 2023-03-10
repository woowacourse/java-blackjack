package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import blackjack.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
    }

    @Test
    @DisplayName("Bank에 플레이어의 배팅 금액을 저장한다.")
    void saveBettingMoney() {
        final Player player = new Player("player1");
        final Money money = new Money(1000);

        dealer.saveBettingMoney(player, money);

        assertThat(dealer.getBank().findMoney(player)).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("플레이어와 본인에게 2장의 카드씩 나눠준다.")
    void settingCardsTest() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Players players = new Players(List.of(player1, player2));

        assertThatNoException().isThrownBy(
                () -> dealer.settingCards(players)
        );
    }
}

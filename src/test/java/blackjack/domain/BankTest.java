package blackjack.domain;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;
import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.K;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.money.Bank;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class BankTest {

    private final Player player = new Player("player1");
    private final Money money = new Money(10000);
    private Bank bank;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
    }

    @Test
    @DisplayName("플레이어의 배팅 금액을 가진다.")
    void betMoneyTest() {
        bank.betMoney(player, money);

        assertThat(bank.getBank().get(player)).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("플레이어의 배팅 금액을 반환한다.")
    void findMoney() {
        bank.betMoney(player, money);

        assertThat(bank.findMoney(player)).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("블랙잭으로 이긴 경우 원금의 1.5배를 반환받는다.")
    void exchangeTest_blackjack() {
        player.receiveHand(new Hand(List.of(
                new Card(ACE, HEART),
                new Card(K, HEART)
        )));

        Bank newBank = bank.betMoney(player, money);

        assertThat(newBank.exchange(player, WIN)).isEqualTo(new Money(15000));
    }

    @Test
    @DisplayName("이긴 경우 원금만큼 반환받는다.")
    void exchangeTest_win() {
        player.receiveHand(new Hand(List.of(
                new Card(ACE, HEART),
                new Card(NINE, HEART)
        )));

        Bank newBank = bank.betMoney(player, money);

        assertThat(newBank.exchange(player, WIN)).isEqualTo(new Money(20000));
    }

    @Test
    @DisplayName("비긴 경우 원금을 반환받는다.")
    void exchangeTest_draw() {
        Bank newBank = bank.betMoney(player, money);

        assertThat(newBank.exchange(player, DRAW)).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("진 경우 원금을 모두 잃는다.")
    void exchangeTest_lose() {
        Bank newBank = bank.betMoney(player, money);

        assertThat(newBank.exchange(player, LOSE)).isEqualTo(new Money(0));
    }

    @Test
    @DisplayName("가진 금액을 모두 반환한다.")
    void totalMoneyTest() {
        bank.betMoney(new Player("player1"), new Money(1000));
        bank.betMoney(new Player("player1"), new Money(200));
        bank.betMoney(new Player("player1"), new Money(30));
        bank.betMoney(new Player("player1"), new Money(4));

        Money totalMoney = bank.totalMoney();

        assertThat(totalMoney).isEqualTo(new Money(1234));
    }
}

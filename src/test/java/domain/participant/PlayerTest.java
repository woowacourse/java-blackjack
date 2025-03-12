package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.bet.BetMoney;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.result.WinLossResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어는 핸드의 총합값을 반환할 수 있다")
    void test1() {
        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TWO, Suit.CLUB));
        player.addCard(new Card(Denomination.FOUR, Suit.CLUB));
        player.addCard(new Card(Denomination.FIVE, Suit.DIAMOND));

        assertThat(player.getHandTotal()).isEqualTo(11);
    }

    @Test
    @DisplayName("플레이어는 베팅을 할 수 있다.")
    void test2() {
        Player player = new Player("moru");

        assertThat(player.bet(10000).getBetMoney()).isEqualTo(new BetMoney(10000));
    }

    @Test
    @DisplayName("승리 시 베팅 금액은 2배가 된다.")
    void test3() {
        Player player = new Player("moru").bet(10000);

        assertThat(player.computeBetResult(WinLossResult.WIN)).isEqualTo(new BetMoney(20000));
    }

    @Test
    @DisplayName("블랙잭으로 승리 시 베팅 금액은 1.5배가 된다.")
    void test4() {
        Player player = new Player("moru").bet(10000);

        assertThat(player.computeBetResult(WinLossResult.BLACKJACK_WIN)).isEqualTo(new BetMoney(15000));
    }

    @Test
    @DisplayName("패배 시 베팅 금액은 0배가 된다.")
    void test5() {
        Player player = new Player("moru").bet(10000);

        assertThat(player.computeBetResult(WinLossResult.LOSS)).isEqualTo(new BetMoney(0));
    }
}

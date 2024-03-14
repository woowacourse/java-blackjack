package blackjack.domain.betting;

import blackjack.domain.player.Name;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankTest {
    @Test
    void 플레이어에게_돈을_송금한다() {
        Bank bank = new Bank();
        bank.transfer(new Name("망쵸"), new Cash(1000));

        // TODO: 한 테스트에서 여러 메서드를 테스트하고 있다.
        Cash balance = bank.checkBalance(new Name("망쵸"));

        assertThat(balance).isEqualTo(new Cash(1000));
    }

//    @Test
//    void 패배한_플레이어의_전체_잔고를_승리한_플레이어의_잔고로_송금한다() {
//        Casino casino = new Casino();
//        Name dealerName = new Name("딜러");
//        Name playerName = new Name("망쵸");
//
//        casino.transferBalanceFromLoserToWinner(dealerName, playerName, WinningStatus.WIN);
//
//        assertThat(casino.checkBalance(dealerName)).isEqualTo(new Cash(1000));
//        assertThat(casino.checkBalance(playerName)).isEqualTo(new Cash(0));
//    }
}

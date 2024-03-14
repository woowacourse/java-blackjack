package blackjack.domain.betting;

import blackjack.domain.player.Name;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CasinoTest {
    @Test
    void 플레이어에게_돈을_송금한다() {
        Casino casino = new Casino();
        casino.transfer(new Name("망쵸"), new Cash(1000));

        // TODO: 한 테스트에서 여러 메서드를 테스트하고 있다.
        Cash balance = casino.checkBalance(new Name("망쵸"));

        assertThat(balance).isEqualTo(new Cash(1000));
    }
}

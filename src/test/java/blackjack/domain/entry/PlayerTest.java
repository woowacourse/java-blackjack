package blackjack.domain.entry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import blackjack.domain.entry.vo.BettingMoney;
import blackjack.domain.entry.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 이름이 딜러인 경우 예외를 발생한다.")
    void throwExceptionContainsDealerName() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Player(new Name("딜러"), new BettingMoney(10000)))
            .withMessage("플레이어의 이름은 딜러의 이름이 될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 같은지 비교할 수 있다.")
    void equalsName() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));

        assertThat(player.equalsName(new Name("hoho"))).isTrue();
    }
}

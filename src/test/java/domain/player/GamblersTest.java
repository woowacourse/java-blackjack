package domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.BettingMoney;
import exception.BlackjackException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("이름이 중복되면 안된다.")
    void 이름이_중복될_시() {
        //given
        Gambler tobiOriginal = new Gambler("tobi", new BettingMoney(1000));
        Gambler tobiFake = new Gambler("tobi", new BettingMoney(1000));
        List<Gambler> gamblers = List.of(tobiOriginal, tobiFake);

        //when & then
        assertThatThrownBy(() -> new Gamblers(gamblers))
                .isInstanceOf(BlackjackException.class);
    }
}

package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    void 배팅_객체를_생성한다() {
        //given
        final int amount = 10_000;

        //when
        final Betting betting = new Betting(amount);

        //then
        assertThat(betting).isInstanceOf(Betting.class);
    }

    @Test
    void 배팅_금액이_음수이므로_예외가_발생한다() {
        //given
        final int amount = -1;

        //when
        Function<Integer, Betting> function = Betting::new;

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> function.apply(amount));
    }
}

package domain;

import static org.assertj.core.api.Assertions.assertThat;

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
}

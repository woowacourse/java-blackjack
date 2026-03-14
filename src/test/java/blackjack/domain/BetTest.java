package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetTest {

    @Test
    void 배팅_금액은_음수가_들어오면_예외처리한다(){
        Assertions.assertThatThrownBy(() -> Bet.of(-5000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 음수가 나올 수 없습니다.");
    }

    @Test
    void 배팅_금액이_양수로_들어오면_정상입력된다(){
        //given
        int amount = 5000;

        //when
        Bet bet = Bet.of(amount);
        //then
        assertThat(bet).isNotNull();
    }

    @Test
    void 배팅_금액은_초반에_0으로_초기화된다(){
        Bet bet = Bet.init();
        assertThat(bet.getAmount()).isEqualTo(0);
    }

    @Test
    void 배팅_금액이_추가되면_그_금액만큼_늘어난다(){
        Bet bet = Bet.init();
        int amount = 5000;

        Bet add = bet.add(amount);

        assertThat(add.getAmount()).isEqualTo(5000);
    }
}
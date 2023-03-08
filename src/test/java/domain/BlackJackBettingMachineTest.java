package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackBettingMachineTest {

    @DisplayName("해당하는 이름에 할당된 money를 반환한다.")
    @Test
    void find_by_specific_name() {
        //given
        int expected = 1000;
        BlackJackBettingMachine blackJackBettingMachine = new BlackJackBettingMachine();
        blackJackBettingMachine.betMoney("name", expected);
        //when
        int actual = blackJackBettingMachine.findBetMoneyByName("name");
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("해당하는 이름이 존재하지 않으면 예외를 던진다.")
    @Test
    void is_contain_name_throw_exception() {
        //given
        BlackJackBettingMachine blackJackBettingMachine = new BlackJackBettingMachine();
        //when && then
        assertThatThrownBy(() -> blackJackBettingMachine.findBetMoneyByName("expected"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 플레이어입니다.");
    }
}

package domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import expcetion.BlackjackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {
    @Test
    @DisplayName("이름이 숫자면 안된다.")
    void 이름이_숫자일_시(){
        //given
        String name = "121345";

        //when & then
        assertThatThrownBy(()-> new Gambler(name))
                .isInstanceOf(BlackjackException.class);
    }
    @Test
    @DisplayName("이름은 두글자 이상 열글자 미만으로 한다.")
    void 이름이_열글자를_넘을_시(){
        //given
        String max_range_name = "tobiisverygoob";
        String min_range_name = "h";
        //when & then
        assertThatThrownBy(()-> new Gambler(max_range_name))
                .isInstanceOf(BlackjackException.class);
        assertThatThrownBy(()-> new Gambler(min_range_name))
                .isInstanceOf(BlackjackException.class);
    }
}
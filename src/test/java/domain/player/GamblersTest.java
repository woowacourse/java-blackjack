package domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import expcetion.BlackjackException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("이름이 중복되면 안된다.")
    void 이름이_중복될_시(){
        //given
        List<String> names = new ArrayList<>(List.of("tobi","tobi"));

        //when & then
        assertThatThrownBy(()-> new Gamblers(names))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("이름이 숫자면 안된다.")
    void 이름이_숫자일_시(){
        //given
        List<String> names = new ArrayList<>(List.of("12314"));

        //when & then
        assertThatThrownBy(()-> new Gamblers(names))
                .isInstanceOf(BlackjackException.class);
    }
    @Test
    @DisplayName("이름은 두글자 이상 열글자 미만으로 한다.")
    void 이름이_열글자를_넘을_시(){
        //given
        List<String> max_names = new ArrayList<>(List.of("tobipobiluc")); //11글자
        List<String> min_names = new ArrayList<>(List.of("t"));
        //when & then
        assertThatThrownBy(()-> new Gamblers(max_names))
                .isInstanceOf(BlackjackException.class);
        assertThatThrownBy(()-> new Gamblers(min_names))
                .isInstanceOf(BlackjackException.class);
    }

}
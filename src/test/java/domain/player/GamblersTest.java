package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
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

}
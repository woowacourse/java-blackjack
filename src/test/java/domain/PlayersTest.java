package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

public class PlayersTest {

    InputView input;
    @BeforeEach
    void beforeEach() {
         input = new InputView();
    }
    @DisplayName("입력에 따른 Player 객체 생성")
    @Test
    void 이름이_정상적으로_들어왔을때() {
        List<String> names = Arrays.asList("아나키", "포비", "모아");

        /*assertThat()*/
        /*Players players = new Players(playerName);*/

        //assertThat(players.getSize()).isEqualTo(3);

    }
}

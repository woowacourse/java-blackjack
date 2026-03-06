package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Status;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 4~10자인 경우")
    void 정상_테스트_1() {
        assertDoesNotThrow(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "pobi"));
    }

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 4자 미만인 경우")
    void 예외_테스트_1() {
        assertThatThrownBy(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "pob"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 10자 초과인 경우")
    void 예외_테스트_2() {
        assertThatThrownBy(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "jasonjasonj"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}

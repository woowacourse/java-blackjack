package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Status;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Nested
    @DisplayName("닉네임 처리 테스트")
    class 닉네임_처리_테스트 {

        @Test
        @DisplayName("닉네임이 4~10자인 경우")
        void 닉네임이_4_10자인_경우() {
            assertDoesNotThrow(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "pobi"));
        }

        @Test
        @DisplayName("닉네임이 4자 미만인 경우")
        void 닉네임이_4자_미만인_경우() {
            assertThatThrownBy(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "pob"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임은 4자 이상이어야 합니다.");
        }

        @Test
        @DisplayName("닉네임이 10자 초과인 경우")
        void 닉네임이_10자_초과인_경우() {
            assertThatThrownBy(() ->  new Player(new Hand(new ArrayList<>()), Status.HIT, "jasonjasonj"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임은 10자 이하여야 합니다.");
        }
    }
}

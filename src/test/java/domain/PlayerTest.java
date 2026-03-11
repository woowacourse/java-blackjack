package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.Bet;
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

    @Nested
    @DisplayName("베팅 테스트")
    class 베팅_테스트 {

        @Test
        @DisplayName("정상 테스트")
        void 정상_테스트() {
            Hand hand = new Hand(new ArrayList<>());
            Status status = Status.HIT;
            Player player = new Player(hand, status, "pobi");
            Bet expected = new Bet(10000);

            player.bet(expected);

            assertThat(player).extracting("bet")
                .usingRecursiveComparison()
                .isEqualTo(expected);
        }

        @Test
        @DisplayName("이미 베팅금을 입력한 플레이어인 경우")
        void 이미_베팅금을_입력한_플레이어인_경우() {
            Hand hand = new Hand(new ArrayList<>());
            Status status = Status.HIT;
            Player player = new Player(hand, status, "pobi");
            Bet bet = new Bet(10000);
            player.bet(bet);

            assertThatThrownBy(() -> player.bet(bet))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 플레이어는 이미 베팅금을 입력하였습니다.");
        }
    }
}

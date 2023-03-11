package domain.user;

import domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static domain.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PlayerTest {

    @DisplayName("Player를 생성하면 State는 Ready상태이다")
    @Test
    void newParticipant() {
        Player player = new Player("maco");

        assertThat(player.getState()).isInstanceOf(Ready.class);
    }

    @DisplayName("hit했을 때 정상 상태")
    @Nested
    class NormalStateByHit{

        @DisplayName("ACE를 hit하면 State는 Hit상태이다")
        @Test
        void hit_ace_Hit() {
            Player player = new Player("maco");

            State state = player.hit(SPADE_ACE);

            assertThat(state).isInstanceOf(Hit.class);
        }

        @DisplayName("ACE, TEN을 hit하면 Blackjack상태이다")
        @Test
        void hit_aceTen_Blackjack() {
            Player player = new Player("maco");

            player.hit(SPADE_ACE);
            player.hit(SPADE_TEN);

            assertThat(player.getState()).isInstanceOf(Blackjack.class);
        }

        @DisplayName("TEN, TEN, FIVE를 hit하면 Blackjack상태이다")
        @Test
        void hit_aceTen_Bust() {
            Player player = new Player("maco");

            player.hit(SPADE_TEN);
            player.hit(HEART_TEN);
            player.hit(SPADE_FIVE);

            assertThat(player.getState()).isInstanceOf(Bust.class);
        }
    }

    @DisplayName("hit했을 때 비정상 상태")
    @Nested
    class AbnormalStateByHit{

        @DisplayName("Bust상태에서 hit할 수 없다.")
        @Test
        void hit_Bust_Exception() {
            Player player = new Player("maco");

            player.hit(SPADE_TEN);
            player.hit(CLOVER_TEN);
            player.hit(DIAMOND_TEN);

            assertThatThrownBy(() -> player.hit(CLOVER_FIVE)).isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("Stay상태에서 hit할 수 없다.")
        @Test
        void hit_Stay_Exception() {
            Player player = new Player("maco");

            player.hit(SPADE_TEN);
            player.hit(CLOVER_TEN);
            player.stay();

            assertThatThrownBy(() -> player.hit(CLOVER_FIVE)).isInstanceOf(IllegalStateException.class);
        }
    }

    @DisplayName("stay하면 Stay상태이다")
    @Test
    void stay() {
        Player player = new Player("maco");

        player.hit(SPADE_TEN);
        player.hit(HEART_TEN);
        player.stay();

        assertThat(player.getState()).isInstanceOf(Stay.class);
    }
}

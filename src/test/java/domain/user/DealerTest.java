package domain.user;

import domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static domain.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DealerTest {
    @DisplayName("Dealer를 생성하면 State는 Ready상태이다")
    @Test
    void newDealer() {
        Dealer dealer = new Dealer("maco");

        assertThat(dealer.getState()).isInstanceOf(Ready.class);
    }

    @DisplayName("hit했을 때 정상 상태")
    @Nested
    class NormalStateByHit{

        @DisplayName("ACE를 hit하면 State는 Hit상태이다")
        @Test
        void hit_ace_Hit() {
            Dealer dealer = new Dealer("maco");

            State state = dealer.hit(SPADE_ACE);

            assertThat(state).isInstanceOf(Hit.class);
        }

        @DisplayName("ACE, TEN을 hit하면 Blackjack상태이다")
        @Test
        void hit_aceTen_Blackjack() {
            Dealer dealer = new Dealer("maco");

            dealer.hit(SPADE_ACE);
            dealer.hit(SPADE_TEN);

            assertThat(dealer.getState()).isInstanceOf(Blackjack.class);
        }

        @DisplayName("TEN, TEN, FIVE를 hit하면 Blackjack상태이다")
        @Test
        void hit_aceTen_Bust() {
            Dealer dealer = new Dealer("maco");

            dealer.hit(SPADE_TEN);
            dealer.hit(HEART_TEN);
            dealer.hit(SPADE_FIVE);

            assertThat(dealer.getState()).isInstanceOf(Bust.class);
        }
    }

    @DisplayName("hit했을 때 비정상 상태")
    @Nested
    class AbnormalStateByHit{

        @DisplayName("Bust상태에서 hit할 수 없다.")
        @Test
        void hit_Bust_Exception() {
            Dealer dealer = new Dealer("maco");

            dealer.hit(SPADE_TEN);
            dealer.hit(CLOVER_TEN);
            dealer.hit(DIAMOND_TEN);

            assertThatThrownBy(() -> dealer.hit(CLOVER_FIVE)).isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("Stay상태에서 hit할 수 없다.")
        @Test
        void hit_Stay_Exception() {
            Dealer dealer = new Dealer("maco");

            dealer.hit(SPADE_TEN);
            dealer.hit(CLOVER_TEN);
            dealer.stay();

            assertThatThrownBy(() -> dealer.hit(CLOVER_FIVE)).isInstanceOf(IllegalStateException.class);
        }
    }

    @DisplayName("stay하면 Stay상태이다")
    @Test
    void stay() {
        Dealer dealer = new Dealer("maco");

        dealer.hit(SPADE_TEN);
        dealer.hit(HEART_TEN);
        dealer.stay();

        assertThat(dealer.getState()).isInstanceOf(Stay.class);
    }
}

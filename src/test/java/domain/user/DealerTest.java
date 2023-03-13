package domain.user;

import domain.state.*;
import domain.state.exceptions.CanNotDrawCardException;
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

    @DisplayName("16이하에서 hit할 수 있다")
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

        @DisplayName("TEN, SIX를 hit하면 Hit 상태이다")
        @Test
        void hit_aceTen_Bust() {
            Dealer dealer = new Dealer("maco");

            dealer.hit(SPADE_TEN);
            dealer.hit(HEART_SIX);

            assertThat(dealer.getState()).isInstanceOf(Hit.class);
        }
    }

    @DisplayName("16초과에서 hit할 수 없다")
    @Nested
    class AbnormalStateByHit{

        @DisplayName("처음 뽑은 카드 두장의 합이 17이면 카드를 뽑지 못한다.")
        @Test
        void canHit() {
            Dealer dealer = new Dealer("maco");

            dealer.hit(DIAMOND_EIGHT);
            dealer.hit(CLOVER_NINE);

            assertThat(dealer.canHit()).isFalse();
        }

        @DisplayName("Stay상태에서 hit할 수 없다.")
        @Test
        void hit_Stay_Exception() {
            Dealer dealer = new Dealer("maco");

            dealer.hit(SPADE_TEN);
            dealer.hit(CLOVER_TEN);
            dealer.stay();

            assertThatThrownBy(() -> dealer.hit(CLOVER_FIVE)).isInstanceOf(CanNotDrawCardException.class);
        }
    }

    @DisplayName("22이면 Bust상태이다")
    @Test
    void hit_eightEightSix_Bust() {
        Dealer dealer = new Dealer("maco");

        dealer.hit(CLOVER_EIGHT);
        dealer.hit(SPADE_EIGHT);
        State state = dealer.hit(HEART_SIX);

        assertThat(state).isInstanceOf(Bust.class);
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

    @DisplayName("처음 뽑은 카드 두장의 합이 16이면 한장 더 뽑을 수 있다.")
    @Test
    void canHit_sixteen_true() {
        Dealer dealer = new Dealer("maco");

        dealer.hit(DIAMOND_EIGHT);
        dealer.hit(CLOVER_EIGHT);

        assertThat(dealer.canHit()).isTrue();
    }


}

package domain.user;

import domain.state.*;
import domain.state.exceptions.CanNotDrawCardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static domain.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantTest {

    @DisplayName("Participant를 생성하면 State는 Ready상태이다")
    @Test
    void newParticipant() {
        Participant participant = new Participant("maco");

        assertThat(participant.getState()).isInstanceOf(Ready.class);
    }

    @DisplayName("hit했을 때 정상 상태")
    @Nested
    class NormalStateByHit{

        @DisplayName("ACE를 hit하면 State는 Hit상태이다")
        @Test
        void hit_ace_Hit() {
            Participant participant = new Participant("maco");

            State state = participant.hit(SPADE_ACE);

            assertThat(state).isInstanceOf(Hit.class);
        }

        @DisplayName("ACE, TEN을 hit하면 Blackjack상태이다")
        @Test
        void hit_aceTen_Blackjack() {
            Participant participant = new Participant("maco");

            participant.hit(SPADE_ACE);
            participant.hit(SPADE_TEN);

            assertThat(participant.getState()).isInstanceOf(Blackjack.class);
        }

        @DisplayName("TEN, TEN, FIVE를 hit하면 Bust상태이다")
        @Test
        void hit_aceTen_Bust() {
            Participant participant = new Participant("maco");

            participant.hit(SPADE_TEN);
            participant.hit(HEART_TEN);
            participant.hit(SPADE_FIVE);

            assertThat(participant.getState()).isInstanceOf(Bust.class);
        }
    }

    @DisplayName("hit했을 때 비정상 상태")
    @Nested
    class AbnormalStateByHit{

        @DisplayName("Bust상태에서 hit할 수 없다.")
        @Test
        void hit_Bust_Exception() {
            Participant participant = new Participant("maco");

            participant.hit(SPADE_TEN);
            participant.hit(CLOVER_TEN);
            participant.hit(DIAMOND_TEN);

            assertThatThrownBy(() -> participant.hit(CLOVER_FIVE)).isInstanceOf(CanNotDrawCardException.class);
        }

        @DisplayName("Stay상태에서 hit할 수 없다.")
        @Test
        void hit_Stay_Exception() {
            Participant participant = new Participant("maco");

            participant.hit(SPADE_TEN);
            participant.hit(CLOVER_TEN);
            participant.stay();

            assertThatThrownBy(() -> participant.hit(CLOVER_FIVE)).isInstanceOf(CanNotDrawCardException.class);
        }
    }

    @DisplayName("stay하면 Stay상태이다")
    @Test
    void stay() {
        Participant participant = new Participant("maco");

        participant.hit(SPADE_TEN);
        participant.hit(HEART_TEN);
        participant.stay();

        assertThat(participant.getState()).isInstanceOf(Stay.class);
    }
}

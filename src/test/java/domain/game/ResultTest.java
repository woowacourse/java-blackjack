package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class ResultTest {

    @Nested
    class BurstTest {

        @Test
        @DisplayName("유저가 Bust 되면 DEFEAT를 반환한다.")
        void givenBurstParticipant_thenResultDEFEAT() {
            //given
            final Participant participant = Participant.of("준팍", 26);
            final Dealer dealer = Dealer.create(20);

            //when
            final ResultType resultType = ResultType.of(dealer, participant);

            //then
            assertThat(resultType).isEqualTo(ResultType.DEFEAT);
        }

        @Test
        @DisplayName("딜러가 Bust 되면 VICTORY를 반환한다.")
        void givenBurstDealer_thenResultVICTORY() {
            //given
            final Participant participant = Participant.of("준팍", 20);
            final Dealer dealer = Dealer.create(26);

            //when
            final ResultType resultType = ResultType.of(dealer, participant);

            //then
            assertThat(resultType).isEqualTo(ResultType.VICTORY);
        }

    }

    @Test
    @DisplayName("딜러와 참가자 모두 Burst 되면 DEFEAT를 반환한다.")
    void givenBothBurst_thenResultDEFEAT() {
        //given
        final Participant participant = Participant.of("준팍", 22);
        final Dealer dealer = Dealer.create(22);

        //when
        final ResultType resultType = ResultType.of(dealer, participant);

        //then
        assertThat(resultType).isEqualTo(ResultType.DEFEAT);
    }

    @Test
    @DisplayName("딜러와 참가자의 점수가 같으면 TIE를 반환한다.")
    void givenSameScore_thenResultTIE() {
        //given
        final Participant participant = Participant.of("준팍", 20);
        final Dealer dealer = Dealer.create(20);

        //when
        final ResultType resultType = ResultType.of(dealer, participant);

        //then
        assertThat(resultType).isEqualTo(ResultType.TIE);
    }

    @Test
    @DisplayName("딜러의 점수가 높으면 DEFEAT를 반환한다.")
    void givenDealerWin_thenResultDEFEAT() {
        //given
        final Participant participant = Participant.of("준팍", 20);
        final Dealer dealer = Dealer.create(21);

        //when
        final ResultType resultType = ResultType.of(dealer, participant);

        //then
        assertThat(resultType).isEqualTo(ResultType.DEFEAT);
    }

    @Test
    @DisplayName("참가자의 점수가 높으면 VICTORY를 반환한다.")
    void givenParticipantWin_thenResultVICTORY() {
        //given
        final Participant participant = Participant.of("준팍", 21);
        final Dealer dealer = Dealer.create(20);

        //when
        final ResultType resultType = ResultType.of(dealer, participant);

        //then
        assertThat(resultType).isEqualTo(ResultType.VICTORY);
    }
}

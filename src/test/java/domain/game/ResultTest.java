package domain.game;

import domain.card.Deck;
import domain.card.Rank;
import domain.card.TestCardGenerator;
import domain.player.Dealer;
import domain.player.Participant;

import domain.player.info.PlayerInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class ResultTest {

    private static final PlayerInfo playerInfo = new PlayerInfo.PlayerInfoBuilder("준팍")
            .setBetAmount(1000)
            .build();

    @Nested
    class BurstTest {

        @Test
        @DisplayName("유저가 Bust 되면 DEFEAT를 반환한다.")
        void givenBurstParticipant_thenResultDEFEAT() {
            //given
            final Participant participant = createParticipantWith(List.of(Rank.TEN, Rank.JACK, Rank.NINE));


            final Dealer dealer = createDealerWith(List.of(Rank.TEN, Rank.JACK));

            //when
            final ResultType resultType = ResultType.of(dealer, participant);

            //then
            assertThat(resultType).isEqualTo(ResultType.DEFEAT);
        }

        @Test
        @DisplayName("딜러가 Bust 되면 VICTORY를 반환한다.")
        void givenBurstDealer_thenResultVICTORY() {
            //given
            final Participant participant = createParticipantWith(List.of(Rank.TEN, Rank.JACK));


            final Dealer dealer = createDealerWith(List.of(Rank.TEN, Rank.JACK, Rank.NINE));

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
        final Participant participant = createParticipantWith(List.of(Rank.TEN, Rank.JACK, Rank.EIGHT));


        final Dealer dealer = createDealerWith(List.of(Rank.TEN, Rank.JACK, Rank.NINE));

        //when
        final ResultType resultType = ResultType.of(dealer, participant);

        //then
        assertThat(resultType).isEqualTo(ResultType.DEFEAT);
    }

    @Test
    @DisplayName("딜러와 참가자의 점수가 같으면 TIE를 반환한다.")
    void givenSameScore_thenResultTIE() {
        //given
        final Participant participant = createParticipantWith(List.of(Rank.TEN, Rank.JACK));


        final Dealer dealer = createDealerWith(List.of(Rank.TEN, Rank.JACK));

        //when
        final ResultType resultType = ResultType.of(dealer, participant);

        //then
        assertThat(resultType).isEqualTo(ResultType.TIE);
    }

    @Test
    @DisplayName("딜러의 점수가 높으면 DEFEAT를 반환한다.")
    void givenDealerWin_thenResultDEFEAT() {
        //given
        final Participant participant = createParticipantWith(List.of(Rank.TEN, Rank.JACK));


        final Dealer dealer = createDealerWith(List.of(Rank.ACE, Rank.JACK));

        //when
        final ResultType resultType = ResultType.of(dealer, participant);

        //then
        assertThat(resultType).isEqualTo(ResultType.DEFEAT);
    }

    @Test
    @DisplayName("참가자의 점수가 높으면 VICTORY를 반환한다.")
    void givenParticipantWin_thenResultVICTORY() {
        final Participant participant = createParticipantWith(List.of(Rank.ACE, Rank.JACK));


        final Dealer dealer = createDealerWith(List.of(Rank.TEN, Rank.JACK));

        //when
        final ResultType resultType = ResultType.of(dealer, participant);

        //then
        assertThat(resultType).isEqualTo(ResultType.VICTORY);
    }

    private static Participant createParticipantWith(final List<Rank> ranks) {
        final Participant participant = Participant.of(playerInfo);

        final Deck participantDeck = Deck.from(TestCardGenerator.from(ranks));
        ranks.forEach(i -> participant.takeCard(participantDeck.dealCard()));

        return participant;
    }

    private static Dealer createDealerWith(final List<Rank> ranks) {
        final Dealer dealer = Dealer.create();

        final Deck dealerDeck = Deck.from(TestCardGenerator.from(ranks));
        ranks.forEach(i -> dealer.takeCard(dealerDeck.dealCard()));

        return dealer;
    }
}

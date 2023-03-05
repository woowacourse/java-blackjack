package domain.game;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
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
        @DisplayName("유저가 Burst 되면 DEFEAT를 반환한다.")
        void givenBurstParticipant_thenResultDEFEAT() {
            //given
            final Participant participant = Participant.from("준팍");
            final Dealer dealer = Dealer.create();

            participant.takeCard(Card.of(Suit.SPADE, Rank.JACK));
            participant.takeCard(Card.of(Suit.SPADE, Rank.SIX));
            participant.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

            dealer.takeCard(Card.of(Suit.SPADE, Rank.JACK));
            dealer.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

            //when
            final Result result = Result.of(dealer, participant);

            //then
            assertThat(result).isEqualTo(Result.DEFEAT);
        }

        @Test
        @DisplayName("딜러가 Burst 되면 VICTORY를 반환한다.")
        void givenBurstDealer_thenResultVICTORY() {
            //given
            final Participant participant = Participant.from("준팍");
            final Dealer dealer = Dealer.create();

            participant.takeCard(Card.of(Suit.SPADE, Rank.JACK));
            participant.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

            dealer.takeCard(Card.of(Suit.SPADE, Rank.JACK));
            dealer.takeCard(Card.of(Suit.SPADE, Rank.SIX));
            dealer.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

            //when
            final Result result = Result.of(dealer, participant);

            //then
            assertThat(result).isEqualTo(Result.VICTORY);
        }

    }

    @Test
    @DisplayName("딜러와 참가자 모두 Burst 되면 DEFEAT를 반환한다.")
    void givenBothBurst_thenResultDEFEAT() {
        //given
        final Participant participant = Participant.from("준팍");
        final Dealer dealer = Dealer.create();

        participant.takeCard(Card.of(Suit.SPADE, Rank.JACK));
        participant.takeCard(Card.of(Suit.SPADE, Rank.SIX));
        participant.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

        dealer.takeCard(Card.of(Suit.SPADE, Rank.JACK));
        dealer.takeCard(Card.of(Suit.SPADE, Rank.SIX));
        dealer.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

        //when
        final Result result = Result.of(dealer, participant);

        //then
        assertThat(result).isEqualTo(Result.DEFEAT);
    }

    @Test
    @DisplayName("딜러와 참가자의 점수가 같으면 TIE를 반환한다.")
    void givenSameScore_thenResultTIE() {
        //given
        final Participant participant = Participant.from("준팍");
        final Dealer dealer = Dealer.create();

        participant.takeCard(Card.of(Suit.SPADE, Rank.JACK));
        participant.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

        dealer.takeCard(Card.of(Suit.SPADE, Rank.JACK));
        dealer.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

        //when
        final Result result = Result.of(dealer, participant);

        //then
        assertThat(result).isEqualTo(Result.TIE);
    }

    @Test
    @DisplayName("딜러의 점수가 높으면 DEFEAT를 반환한다.")
    void givenDealerWin_thenResultDEFEAT() {
        //given
        final Participant participant = Participant.from("준팍");
        final Dealer dealer = Dealer.create();

        participant.takeCard(Card.of(Suit.SPADE, Rank.JACK));
        participant.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

        dealer.takeCard(Card.of(Suit.SPADE, Rank.JACK));
        dealer.takeCard(Card.of(Suit.DIAMOND, Rank.ACE));

        //when
        final Result result = Result.of(dealer, participant);

        //then
        assertThat(result).isEqualTo(Result.DEFEAT);
    }

    @Test
    @DisplayName("참가자의 점수가 높으면 VICTORY를 반환한다.")
    void givenParticipantWin_thenResultVICTORY() {
        //given
        final Participant participant = Participant.from("준팍");
        final Dealer dealer = Dealer.create();

        participant.takeCard(Card.of(Suit.SPADE, Rank.JACK));
        participant.takeCard(Card.of(Suit.DIAMOND, Rank.ACE));

        dealer.takeCard(Card.of(Suit.SPADE, Rank.JACK));
        dealer.takeCard(Card.of(Suit.DIAMOND, Rank.JACK));

        //when
        final Result result = Result.of(dealer, participant);

        //then
        assertThat(result).isEqualTo(Result.VICTORY);
    }
}

package domain.player;

import domain.card.Deck;
import domain.card.Rank;
import domain.card.TestCardGenerator;
import domain.player.info.ParticipantInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

    private static final ParticipantInfo PARTICIPANT_INFO = new ParticipantInfo.ParticipantBuilder("준팍")
            .setBetAmount(1000)
            .build();

    @Nested
    @DisplayName("유저가 카드를 더 뽑을 때,")
    class HitTest {

        private static final boolean isHit = true;

        @Test
        @DisplayName("카드 점수의 총합이 22 미만이면 true를 리턴한다.")
        void giveUnderBustScore_thenReturnTrue() {
            //given
            final Participant participant = Participant.of(PARTICIPANT_INFO);

            final List<Rank> ranks = List.of(Rank.TEN, Rank.ACE);
            final Deck deck = Deck.from(TestCardGenerator.from(ranks));

            ranks.forEach(i -> participant.takeCard(deck.dealCard()));

            //when
            final boolean keepGaming = participant.isHit(isHit);

            //then
            assertThat(keepGaming).isTrue();
        }

        @Test
        @DisplayName("카드 점수의 총합이 22 이상이면 false를 리턴한다.")
        void giveBustScore_thenReturnFalse() {
            //given
            final Participant participant = Participant.of(PARTICIPANT_INFO);

            final List<Rank> ranks = List.of(Rank.TEN, Rank.JACK, Rank.NINE);
            final Deck deck = Deck.from(TestCardGenerator.from(ranks));

            ranks.forEach(i -> participant.takeCard(deck.dealCard()));

            //when
            final boolean keepGaming = participant.isHit(isHit);

            //then
            assertThat(keepGaming).isFalse();
        }
    }

    @Nested
    @DisplayName("유저가 카드를 더 뽑지 않을 때,")
    class BustTest {

        private static final boolean isHit = false;

        @Test
        @DisplayName("카드 점수의 총합이 22 미만이어도 false를 리턴한다.")
        void giveUnderBustScore_thenReturnTrue() {
            //given
            final Participant participant = Participant.of(PARTICIPANT_INFO);

            final List<Rank> ranks = List.of(Rank.TEN, Rank.ACE);
            final Deck deck = Deck.from(TestCardGenerator.from(ranks));

            ranks.forEach(i -> participant.takeCard(deck.dealCard()));

            //when
            final boolean keepGaming = participant.isHit(isHit);

            //then
            assertThat(keepGaming).isFalse();
        }

        @Test
        @DisplayName("카드 점수의 총합이 22 이상이면 false를 리턴한다.")
        void giveBustScore_thenReturnFalse() {
            //given
            final Participant participant = Participant.of(PARTICIPANT_INFO);

            final List<Rank> ranks = List.of(Rank.TEN, Rank.JACK, Rank.NINE);
            final Deck deck = Deck.from(TestCardGenerator.from(ranks));

            ranks.forEach(i -> participant.takeCard(deck.dealCard()));

            //when
            final boolean keepGaming = participant.isHit(isHit);

            //then
            assertThat(keepGaming).isFalse();
        }
    }
}

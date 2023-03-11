package domain.player;

import domain.card.Deck;
import domain.card.Rank;
import domain.card.TestCardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

    @Test
    @DisplayName("참가자의 이름이 딜러면 예외가 발생한다")
    void givenDealerName_theFailed() {
        assertThatThrownBy(() -> Participant.of("딜러", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 이름은 딜러가 될 수 없습니다.");
    }

    @Nested
    @DisplayName("유저가 카드를 더 뽑을 때,")
    class HitTest {

        private static final boolean isHit = true;

        @Test
        @DisplayName("카드 점수의 총합이 22 미만이면 true를 리턴한다.")
        void giveUnderBustScore_thenReturnTrue() {
            //given
            final Participant participant = Participant.of("준팍", 0);

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
            final Participant participant = Participant.of("준팍", 0);

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
            final Participant participant = Participant.of("준팍", 0);

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
            final Participant participant = Participant.of("준팍", 0);

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

package domain.player.participant;

import domain.card.Card;
import domain.player.Name;
import domain.player.participant.betresult.BetResultState;
import domain.player.participant.betresult.BreakEvenState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.ACE;
import static domain.card.CardValue.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Participant 은")
class ParticipantTest {

    final Participant participant = new Participant(new Name("name"));

    @BeforeEach
    void makeCardScoreTwenty() {
        participant.hit(new Card(CLOVER, TEN));
        participant.hit(new Card(SPADE, TEN));
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 미만일 경우 카드를 더 받을 수 있다.")
    void test_canHit_underScore21() {
        // when & then
        assertTrue(participant.canHit());
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 이상일 경우 카드를 더 받을 수 없다.")
    void test_canHit_overScore21() {
        // when
        participant.hit(new Card(CLOVER, ACE));

        // then
        assertFalse(participant.canHit());
    }

    @Test
    @DisplayName("determineBetState() : 참여자는 베팅 결과를 결정할 수 있다.")
    void test_determineBetState() throws Exception {
        //given
        assertThat(participant).extracting("betResultState")
                               .isNull();

        //when
        participant.determineBetState(new BreakEvenState());

        //then
        assertThat(participant).extracting("betResultState")
                               .isInstanceOf(BetResultState.class);
    }
}

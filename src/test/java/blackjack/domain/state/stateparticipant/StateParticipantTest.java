package blackjack.domain.state.stateparticipant;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Name;
import blackjack.domain.state.State;
import blackjack.domain.state.started.Started;
import blackjack.domain.state.started.finished.Blackjack;
import blackjack.domain.state.started.running.Running;

public class StateParticipantTest {

    @Test
    @DisplayName("생성한 참여자의 상태가 Started인지 확인")
    void checkStarted() {
        //given
        StateParticipant participant = new StatePlayer(Name.of("pobi"));

        //when
        State actual = participant.getState();

        //then
        assertThat(actual).isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("초기화 후 참여자의 상태가 Running인지 확인")
    void checkRunning() {
        //given
        StateParticipant participant = new StatePlayer(Name.of("pobi"));

        //when
        participant.init(new Card(DIAMOND, JACK),
            new Card(HEART, JACK));
        State actual = participant.getState();

        //then
        assertThat(actual).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("초기화 후 카드 합이 21이면 상태가 Blackjack인지 확인")
    void checkBlackjack() {
        //given
        StateParticipant participant = new StatePlayer(Name.of("pobi"));

        //when
        participant.init(new Card(DIAMOND, JACK),
            new Card(HEART, ACE));
        State actual = participant.getState();

        //then
        assertThat(actual).isInstanceOf(Blackjack.class);
    }
}

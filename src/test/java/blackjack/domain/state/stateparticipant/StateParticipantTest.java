package blackjack.domain.state.stateparticipant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.participant.Name;
import blackjack.domain.state.State;
import blackjack.domain.state.started.Started;
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
        participant.init(new Card(CardSymbol.DIAMOND, CardNumber.JACK),
            new Card(CardSymbol.HEART, CardNumber.JACK));
        State actual = participant.getState();

        //then
        assertThat(actual).isInstanceOf(Running.class);
    }
}

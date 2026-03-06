package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Emblem;
import domain.card.Grade;
import domain.participant.GameState;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Participant;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    @Test
    void 카드의_합이_21이초과되면_버스트() {
        // given
        Hand hand = new Hand();
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));

        Participant participant = new Participant(new Name("test"), hand);
        boolean expect = true;

        // when
        boolean result = participant.isBust();

        // then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    void 카드의_합이_21이하이면_버스트가_아니다() {
        // given
        Hand hand = new Hand();
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));

        Participant participant = new Participant(new Name("test"), hand);
        boolean expect = false;

        // when
        boolean result = participant.isBust();

        // then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    void 플레이어의_게임_진행_상태를_변경한다() {
        // given
        Participant participant = new Participant(new Name("test"), new Hand());
        GameState expectedState = GameState.STAND;
        // when
        participant.changeState();

        // then
        assertThat(participant.getGameState()).isEqualTo(expectedState);
    }
}

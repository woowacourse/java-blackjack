package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("null으로 생성하려는 경우 예외를 발생시킨다.")
    void createExceptionByNull() {
        assertThatThrownBy(() -> new Participants(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("players는 null로 생성할 수 없습니다.");
    }

    @Test
    @DisplayName("중복된 이름들로 생성 시 예외를 발생시킨다.")
    void createExceptionByDuplication() {
        final Participant firstplayer = Player
                .createNewPlayer("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Participant secondplayer = Player
                .createNewPlayer("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final List<Participant> participants = Arrays.asList(firstplayer, secondplayer);

        assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름 간에 중복이 있으면 안됩니다.");
    }

    @Test
    @DisplayName("현재 턴의 플레이어가 버스트될 경우 다음 플레이어로 턴을 넘긴다.")
    void drawCurrentPlayerIsBust() {
        final Participant participant = Player
                .createNewPlayer("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Participants participants = new Participants(Collections.singletonList(participant));
        participants.drawCurrentPlayer(Card.of(SPADE, JACK));
        assertThat(participants.isAllTurnEnd()).isTrue();
    }

    @Test
    @DisplayName("모든 플레이어의 턴이 종료되었는데 드로우하려고하면 예외가 발생해야 한다.")
    void drawCurrentPlayerExceptionByEndAllTurn() {
        final Participant participant =
                Player.createNewPlayer("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Participants participants = new Participants(Collections.singletonList(participant));
        participants.drawCurrentPlayer(Card.of(SPADE, JACK));

        assertThatThrownBy(() -> participants.drawCurrentPlayer(Card.of(SPADE, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }

    @Test
    @DisplayName("모든 턴이 종료되었을 때 턴 증가를 할 수 없다.")
    void turnToNextPlayerExceptionByEndAllTurn() {
        final Participant participant =
                Player.createNewPlayer("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Participants participants = new Participants(Collections.singletonList(participant));
        participants.turnToNextPlayer();

        assertThatThrownBy(() -> participants.turnToNextPlayer())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }

    @Test
    @DisplayName("모든 턴이 종료되었을 때 현재 플레이어 정보를 반환하려하면 예외가 발생한다.")
    void getCurrentTurnPlayerCardExceptionByEndAllTurn() {
        final Participant participant = Player
                .createNewPlayer("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Participants participants = new Participants(Collections.singletonList(participant));
        participants.turnToNextPlayer();

        assertThatThrownBy(() -> participants.getCurrentTurnPlayerCards())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }

    @Test
    @DisplayName("모든 턴이 종료되었을 때 현재 플레이어 이름 반환하려하면 예외가 발생한다.")
    void getCurrentTurnPlayerNameExceptionByEndAllTurn() {
        final Participant participant = Player
                .createNewPlayer("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Participants participants = new Participants(Collections.singletonList(participant));
        participants.turnToNextPlayer();

        assertThatThrownBy(() -> participants.getCurrentTurnPlayerName())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }
}